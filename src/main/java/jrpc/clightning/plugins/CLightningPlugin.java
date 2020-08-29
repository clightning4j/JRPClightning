/**
 * This is a wrapper for c-lightning RPC interface.
 * Copyright (C) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jrpc.clightning.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import jrpc.clightning.annotation.Hook;
import jrpc.clightning.annotation.PluginOption;
import jrpc.clightning.annotation.RPCMethod;
import jrpc.clightning.annotation.Subscription;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.plugins.rpcmethods.RPCMethodReflection;
import jrpc.clightning.plugins.rpcmethods.RPCMethodType;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class CLightningPlugin implements ICLightningPlugin {

    @Expose
    private static final Class TAG = CLightningPlugin.class;

    private ManifestMethod manifest;
    @Expose
    protected Map<String, Object> parameters;
    @Expose
    private BufferedWriter stdout;
    @Expose
    private BufferedReader stdin;
    @Expose
    private Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("jrpc.clightning.plugins"))
            .setScanners(new MethodAnnotationsScanner(), new FieldAnnotationsScanner()));

    public CLightningPlugin() {
        this.manifest = new ManifestMethod();
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
        this.stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        this.parameters = new HashMap<>();
    }

    public void addRPCMethod(AbstractRPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        CLightningLogger.getInstance().debug(TAG,"Added method to list methods of plugin");
        if(method.getType() == RPCMethodType.HOOK){
            this.manifest.addHook(method.getName());
            log(CLightningLevelLog.WARNING, "ADD HOCK: " + method.getName());
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
                log(CLightningLevelLog.WARNING, "Request: " +  object.toString());
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
        this.addOptionsWithAnnotation();
        this.registerSubscriptionsWithAnnotation();
        this.registerHooksWithAnnotation();
        addRPCMethod(new InitMethod());
    }

    private void addOptionsWithAnnotation() {
        log(CLightningLevelLog.WARNING, "add option annotated");
        for(Field field : reflections.getFieldsAnnotatedWith(PluginOption.class)){
            log(CLightningLevelLog.WARNING, "field annotated: " + field.getName());
            if(field.isAnnotationPresent(PluginOption.class)){
                log(CLightningLevelLog.WARNING, "Find a plugin option");
                PluginOption annotation = field.getAnnotation(PluginOption.class);
                Option option = new Option();
                //TODO pull the value directly from the annotation propriety
                option.setDefaultValue(annotation.defValue());
                option.setDescriptionOption(annotation.description());
                option.setName(annotation.name());
                option.setType(annotation.typeValue());
                option.setDeprecated(annotation.deprecated());
                this.manifest.addOption(option);
                log(CLightningLevelLog.WARNING, option.getNamePlugin());
            }
        }
    }

    private void registerMethodsWithAnnotation(){
        for(Method method : reflections.getMethodsAnnotatedWith(jrpc.clightning.annotation.RPCMethod.class)){
            if(method.isAnnotationPresent(jrpc.clightning.annotation.RPCMethod.class)){
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
        for(Method method : reflections.getMethodsAnnotatedWith(Hook.class)){
            if(method.isAnnotationPresent(Hook.class)){
                Hook hook = method.getAnnotation(Hook.class);
                String hookEvent = hook.hook();
                RPCMethodReflection hookMethod =  new RPCMethodReflection(
                        hookEvent, "", "", "", RPCMethodType.HOOK, method);
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
        log(CLightningLevelLog.WARNING, method);
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
                rpcMethod.doRun(this, new CLightningJsonObject(request), result);
                CLightningLogger.getInstance().debug(TAG, "Plugin result ++++++ " + response + " ++++++");
                //JsonElement jsonResult = (JsonElement) jsonConverter.deserialization(result, JsonElement.class);
                CLightningLogger.getInstance().debug(TAG, "Plugin result ++++++ " + response + " ++++++");
                response.add("result", result.getWrapper());
                CLightningLogger.getInstance().debug(TAG, "******** final answer: " + response.toString());
                log(CLightningLevelLog.WARNING, response.toString());
                stdout.write(response.toString());
                stdout.flush();
            }
        }
    }

    public void addSubscription(String event){
        manifest.addSubscriptions(event);
    }

    public void addParameter(String key, Object value){
        this.parameters.put(key, value);
    }

    public <T> T getParameter(String key){
        if(key == null || key.isEmpty()){
            throw new IllegalArgumentException("Key is null or empty");
        }
        if(parameters.containsKey(key)){
           return (T) parameters.get(key);
        }
        return null;
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
