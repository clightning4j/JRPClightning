package jrpc.clightning.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import jrpc.clightning.CLightningRPC;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

import java.io.*;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractPlugin implements ICLightningPlugin {

    @Expose
    private static final Class TAG = AbstractPlugin.class;


    private ManifestMethod manifest;
    private BufferedWriter stdout;
    private BufferedReader stdin;

    public AbstractPlugin() {
        this.manifest = new ManifestMethod();
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
        this.stdout = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void addRPCMethod(RPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        CLightningLogger.getInstance().debug(TAG,"Added method to list methods of plugin");
        this.manifest.addMethod(method);
    }

    @Override
    public void start() {
        this.registerMethod();
        //InputStream inputStreamSocket = CLightningRPC.getInstance().getInputStream();
        try {
            String messageSocket;
            JsonConverter jsonConverter = new JsonConverter();
            while ((messageSocket = stdin.readLine()) != null) {
                if (messageSocket.trim().isEmpty()) {
                    continue;
                }
                CLightningLogger.getInstance().debug(TAG, "Message from stdout: " + messageSocket);
                JsonObject object = JsonParser.parseString(messageSocket).getAsJsonObject();
                if (!isRpcCall(object) || !object.has("id")) {
                    continue;
                }
                doMethods(object, jsonConverter, stdout);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override //TODO I'm working here
    public void log(CLightningLevelLog level, String logMessage) {
        if(logMessage == null || logMessage.isEmpty()){
            throw new IllegalArgumentException("Method log in Plugin class: Log message is null or empty");
        }
        CLightningJsonObject payload = new CLightningJsonObject();
        CLightningJsonObject params = new CLightningJsonObject();
        params.add("level", level.getLevel());
        params.add("message", logMessage);
        payload.add("jsonrpc", "2.0");
        payload.add("method", "log");
        payload.add("params", params.getWrapper());
        CLightningLogger.getInstance().debug(TAG, "LOG result: " + payload.toString());

        try {
            this.stdout.write(payload.getWrapper().toString());
            this.stdout.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    protected void registerMethod(){
        addRPCMethod(this.manifest);
        addRPCMethod(new InitMethod());
    }

    private boolean isRpcCall(JsonObject object) {
        if (object == null) {
            throw new IllegalArgumentException("JsonObject null");
        }
        return object.has("method") || object.has("jsonrpc") || object.has("params");
    }

    //TODO remove JSONConverter
    private void doMethods(JsonObject request, JsonConverter jsonConverter, BufferedWriter stdout) throws ServiceException, IOException {
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for execution rpc method");
        String method = request.get("method").getAsString();
        if (method == null || method.isEmpty()) {
            return;
        }
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for method: ++++++ " + method + " ++++++");
        for (RPCMethod rpcMethod : this.getRpcMethods()) {
            CLightningLogger.getInstance().debug(TAG, "Call method plugin ++++++ " + rpcMethod.getName() + " ++++++");
            if (method.trim().equals(rpcMethod.getName())) {
                // Pass the request object and create a response object like Java servlet.
                //String result = rpcMethod.doRun(request); //this method is deprecated
                CLightningJsonObject response = new CLightningJsonObject();
                CLightningJsonObject result = new CLightningJsonObject();
                response.add("id", request.get("id"));
                response.add("jsonrpc", request.get("jsonrpc"));
                rpcMethod.doRun(new CLightningJsonObject(request), result);
                CLightningLogger.getInstance().debug(TAG, "Plugin result ++++++ " + response + " ++++++");
                //JsonElement jsonResult = (JsonElement) jsonConverter.deserialization(result, JsonElement.class);
                CLightningLogger.getInstance().debug(TAG, "Plugin result ++++++ " + response + " ++++++");
                response.add("result", result.getWrapper());
                CLightningLogger.getInstance().debug(TAG, "******** final answer: " + response.toString());
                stdout.write(response.toString());
                stdout.flush();
            }
        }
    }

    // getter method
    public List<RPCMethod> getRpcMethods() {
        return manifest.getRpcMethods();
    }

    public List<String> getSubscriptions() {
        return manifest.getSubscriptions();
    }

    public List<String> getHooks() {
        return manifest.getHooks();
    }

    public boolean isDynamic() {
        return manifest.getDynamic();
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

}
