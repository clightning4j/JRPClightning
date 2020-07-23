package jrpc.clightning.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import jrpc.clightning.plugins.annotation.Hook;
import jrpc.clightning.plugins.annotation.RPCMethod;
import jrpc.clightning.plugins.annotation.Subscription;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.RPCMethodReflection;
import jrpc.clightning.plugins.rpcmethods.RPCMethodType;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class CLightningPlugin implements ICLightningPlugin {

    @Expose
    private static final Class TAG = CLightningPlugin.class;

    private ManifestMethod manifest;
    @Expose
    private BufferedWriter stdout;
    @Expose
    private BufferedReader stdin;
    @Expose
    private Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("jrpc.clightning.plugins"))
            .setScanners(new MethodAnnotationsScanner()));

    public CLightningPlugin() {
        this.manifest = new ManifestMethod();
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
        this.stdout = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void addRPCMethod(AbstractRPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        CLightningLogger.getInstance().debug(TAG,"Added method to list methods of plugin");
        if(method.getType().equals(RPCMethodType.HOOK)){
            this.manifest.addHook(method.getName());
            return;
        }
        this.manifest.addMethod(method);
    }

    @Override
    public void start() {
        this.registerMethod();
        try {
            String messageSocket;
            while ((messageSocket = stdin.readLine()) != null) {
                if (messageSocket.trim().isEmpty()) {
                    continue;
                }
                CLightningLogger.getInstance().debug(TAG, "Message from stdout: " + messageSocket);
                JsonObject object = JsonParser.parseString(messageSocket).getAsJsonObject();
                if (!isRpcCall(object)) {
                    continue;
                }
                if(isNotification(object)){
                    CLightningJsonObject notificationRequest = new CLightningJsonObject(object);
                    for(Method method : reflections.getMethodsAnnotatedWith(Subscription.class)){
                        if(method.isAnnotationPresent(Subscription.class)){
                            Subscription subscription = method.getAnnotation(Subscription.class);
                            String event = subscription.notification();
                            if(notificationRequest.has(event)){
                                method.invoke(this, notificationRequest);
                            }
                        }
                    }
                    continue;
                }
                doMethods(object, stdout);
            }
        } catch (IOException | ServiceException |
                IllegalAccessException | InvocationTargetException ex) {
            log(CLightningLevelLog.ERROR, ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private boolean isNotification(JsonObject object) {
        if(object == null){
            throw new IllegalArgumentException("Rpc call from daemon lightningd null");
        }
        return !object.has("id");
    }

    /**
     * This call should be enable also in RPC methods.
     * @param level level log, this class should be an instance of enum CLightningLevelLog
     * @param logMessage log message should be the log message
     */
    @Override
    public void log(CLightningLevelLog level, String logMessage) {
        if(logMessage == null || logMessage.isEmpty()){
            throw new IllegalArgumentException("Method log in Plugin class: Log message is null or empty");
        }
        CLightningJsonObject payload = new CLightningJsonObject();
        CLightningJsonObject params = new CLightningJsonObject();
        params.add("level", level.getLevel());

        payload.add("jsonrpc", "2.0");
        payload.add("method", "log");
        payload.add("params", params.getWrapper());
        CLightningLogger.getInstance().debug(TAG, "LOG result: " + payload.toString());

        StringTokenizer endLine = new StringTokenizer(logMessage, "\n");
        while (endLine.hasMoreTokens()){
            String line = endLine.nextToken();
            params.remove("message");
            params.add("message", line);

            payload.remove("params");
            payload.add("params", params.getWrapper());
            try {
                this.stdout.write(payload.getWrapper().toString());
                this.stdout.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    protected void registerMethod(){
        addRPCMethod(this.manifest);
        this.registerMethodsWithAnnotation();
        this.registerSubscriptionsWithAnnotation();
        //this.registerHooksWithAnnotation();
        addRPCMethod(new InitMethod());
    }

    private void registerMethodsWithAnnotation(){
        for(Method method : reflections.getMethodsAnnotatedWith(jrpc.clightning.plugins.annotation.RPCMethod.class)){
            if(method.isAnnotationPresent(jrpc.clightning.plugins.annotation.RPCMethod.class)){
                RPCMethod annotation = method.getAnnotation(RPCMethod.class);
                String name = annotation.name();
                String description = annotation.description();
                String longDescription = annotation.longDescription();
                String parameter = annotation.parameter();
                RPCMethodReflection rpcMethod = new RPCMethodReflection(name, parameter, description, longDescription, method);
                this.addRPCMethod(rpcMethod);
            }
        }
    }

    private void registerSubscriptionsWithAnnotation(){
        for(Method method : reflections.getMethodsAnnotatedWith(Subscription.class)){
            if(method.isAnnotationPresent(Subscription.class)){
                Subscription subscription = method.getAnnotation(Subscription.class);
                String event = subscription.notification();
                this.addSubscription(event);
            }
        }
    }

    private void registerHooksWithAnnotation(){
        for(Method method : reflections.getMethodsAnnotatedWith(Subscription.class)){
            if(method.isAnnotationPresent(Hook.class)){
                Hook hook = method.getAnnotation(Hook.class);
                String hookEvent = hook.hook();
                RPCMethodReflection hookMethod =
                        new RPCMethodReflection(hookEvent, "", "", "",
                                RPCMethodType.HOOK, method);
                this.addRPCMethod(hookMethod);
            }
        }
    }

    private boolean isRpcCall(JsonObject object) {
        if (object == null) {
            throw new IllegalArgumentException("JsonObject null");
        }
        return object.has("method") || object.has("jsonrpc") || object.has("params");
    }

    private void doMethods(JsonObject request, BufferedWriter stdout) throws ServiceException, IOException {
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for execution rpc method");
        String method = request.get("method").getAsString();
        if (method == null || method.isEmpty()) {
            return;
        }
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for method: ++++++ " + method + " ++++++");
        for (AbstractRPCMethod rpcMethod : this.getRpcMethods()) {
            CLightningLogger.getInstance().debug(TAG, "Call method plugin ++++++ " + rpcMethod.getName() + " ++++++");
            if (method.trim().equals(rpcMethod.getName())) {
                // Pass the request object and create a response object like Java servlet.
                //String result = rpcMethod.doRun(request); //this method is deprecated
                CLightningJsonObject response = new CLightningJsonObject();
                CLightningJsonObject result = new CLightningJsonObject();
                response.add("id", request.get("id"));
                response.add("jsonrpc", request.get("jsonrpc"));
                rpcMethod.doRun(this, new CLightningJsonObject(request), result); //TODO I'm testing to pass the plugin inside the rpc method
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

    public void addSubscription(String event){
        manifest.addSubscriptions(event);
    }

    // getter method
    public List<AbstractRPCMethod> getRpcMethods() {
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
