/**
 * This is a wrapper for c-lightning RPC interface.
 * Copyright (C) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
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
import jrpc.clightning.model.types.CLightingPluginConfig;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.clightning.plugins.interceptor.Interceptor;
import jrpc.clightning.plugins.interceptor.MappingCmdOptions;
import jrpc.clightning.plugins.log.PluginLog;
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
import java.util.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class CLightningPlugin implements ICLightningPlugin {

    @Expose
    private static final Class TAG = CLightningPlugin.class;

    private ManifestMethod manifest;
    @Expose
    private List<Interceptor> preInterceptor;
    @Expose
    private List<Interceptor> prostInterceptor;
    @Expose
    protected Map<String, Object> parameters;
    @Expose
    private boolean parametersReady;
    @Expose
    private BufferedWriter stdout;
    @Expose
    private BufferedReader stdin;
    @Expose
    private JsonConverter converter;
    @Expose
    protected CLightingPluginConfig configs;
    @Expose
    private Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forJavaClassPath())
            .setScanners(new MethodAnnotationsScanner(), new FieldAnnotationsScanner()));

    public CLightningPlugin() {
        this.manifest = new ManifestMethod();
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
        this.stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        this.parameters = new HashMap<>();
        this.converter = new JsonConverter();
        this.prostInterceptor = new ArrayList<>();
        this.preInterceptor = new ArrayList<>();
        this.prostInterceptor.add(new MappingCmdOptions(reflections));
        this.parametersReady = false;
    }

    public void addRPCMethod(AbstractRPCMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method object null");
        }
        CLightningLogger.getInstance().debug(TAG, "Added method to list methods of plugin");
        if (method.getType() == RPCMethodType.HOOK) {
            this.manifest.addHook(method.getName());
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
                log(PluginLog.DEBUG, messageSocket);
                CLightningLogger.getInstance().debug(TAG, "Message from stdout: " + messageSocket);
                JsonObject object = JsonParser.parseString(messageSocket).getAsJsonObject();
                log(PluginLog.DEBUG, object);
                if (!PluginUtils.isRpcCall(object)) {
                    continue;
                }

                if (PluginUtils.isNotification(object)) {
                    //TODO refactoring this inside the reflectionManager and add a list of methods inside this class
                    CLightningJsonObject notificationRequest = new CLightningJsonObject(object);
                    for (Method method : reflections.getMethodsAnnotatedWith(Subscription.class)) {
                        if (method.isAnnotationPresent(Subscription.class)) {
                            Subscription subscription = method.getAnnotation(Subscription.class);
                            String event = subscription.notification();
                            String methodName = notificationRequest.get("method").getAsString();
                            if (methodName.equals(event)) {
                                method.invoke(this, notificationRequest);
                            }
                        }
                    }
                } else {
                    this.doMethods(object, stdout);
                }
            }
        } catch (IOException | ServiceException |
                IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
            log(PluginLog.ERROR, ex.getLocalizedMessage());
        }
    }

    public void setConfigs(CLightingPluginConfig configs) {
        this.configs = configs;
    }

    public CLightingPluginConfig getConfigs() {
        return configs;
    }

    public boolean hasParametersReady() {
        return parametersReady;
    }

    public void log(PluginLog level, CLightningJsonObject json) {
        this.log(level, json.getWrapper());
    }

    public void log(PluginLog level, Object json) {
        String jsonString = converter.serialization(json);
        this.log(level, jsonString);
    }

    /**
     * This call should be enable also in RPC methods.
     * @param level level log, this class should be an instance of enum CLightningLevelLog
     * @param logMessage log message should be the log message
     */
    @Override
    public void log(PluginLog level, String logMessage) {
        if (logMessage == null) {
            logMessage = "Message empty";
        }
        CLightningJsonObject payload = new CLightningJsonObject();
        CLightningJsonObject params = new CLightningJsonObject();
        params.add("level", level.getLevel());

        payload.add("jsonrpc", "2.0");
        payload.add("method", "log");
        payload.add("params", params.getWrapper());
        CLightningLogger.getInstance().debug(TAG, "LOG result: " + payload.toString());

        //TODO! I don't like that this print each line that have a \n on more debug log
        StringTokenizer endLine = new StringTokenizer(logMessage, "\n");
        while (endLine.hasMoreTokens()) {
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

    protected void registerMethod() {
        addRPCMethod(this.manifest);
        this.registerMethodsWithAnnotation();
        this.addOptionsWithAnnotation();
        this.registerSubscriptionsWithAnnotation();
        this.registerHooksWithAnnotation();
        addRPCMethod(new InitMethod());
    }

    private void addOptionsWithAnnotation() {
        for (Field field : reflections.getFieldsAnnotatedWith(PluginOption.class)) {
            if (field.isAnnotationPresent(PluginOption.class)) {
                PluginOption annotation = field.getAnnotation(PluginOption.class);
                Option option = new Option();
                //TODO pull the value directly from the annotation propriety
                option.setDefaultValue(annotation.defValue());
                option.setDescriptionOption(annotation.description());
                option.setName(annotation.name());
                option.setType(annotation.typeValue());
                option.setDeprecated(annotation.deprecated());
                this.manifest.addOption(option);
            }
        }
    }

    private void registerMethodsWithAnnotation() {
        for (Method method : reflections.getMethodsAnnotatedWith(jrpc.clightning.annotation.RPCMethod.class)) {
            if (method.isAnnotationPresent(jrpc.clightning.annotation.RPCMethod.class)) {
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

    private void registerSubscriptionsWithAnnotation() {
        for (Method method : reflections.getMethodsAnnotatedWith(Subscription.class)) {
            if (method.isAnnotationPresent(Subscription.class)) {
                Subscription subscription = method.getAnnotation(Subscription.class);
                String event = subscription.notification();
                this.addSubscription(event);
            }
        }
    }

    private void registerHooksWithAnnotation() {
        for (Method method : reflections.getMethodsAnnotatedWith(Hook.class)) {
            if (method.isAnnotationPresent(Hook.class)) {
                Hook hook = method.getAnnotation(Hook.class);
                String hookEvent = hook.hook();
                RPCMethodReflection hookMethod = new RPCMethodReflection(
                        hookEvent, "", "", "", RPCMethodType.HOOK, method);
                this.addRPCMethod(hookMethod);
            }
        }
    }

    private void doMethods(JsonObject request, BufferedWriter stdout) throws ServiceException, IOException {
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for execution rpc method");
        String method = request.get("method").getAsString();
        if (method == null || method.isEmpty()) {
            return;
        }
        CLightningLogger.getInstance().debug(TAG, "c-lightning calls for method: ++++++ " + method + " ++++++");
        // TODO refactoring this with a map and not with a list
        for (AbstractRPCMethod rpcMethod : this.getRpcMethods()) {
            CLightningLogger.getInstance().debug(TAG, "Call method plugin ++++++ " + rpcMethod.getName() + " ++++++");
            if (method.trim().equals(rpcMethod.getName())) {
                // Pass the request object and create a response object like Java servlet.
                //String result = rpcMethod.doRun(request); //this method is deprecated
                CLightningJsonObject response = new CLightningJsonObject();
                CLightningJsonObject result = new CLightningJsonObject();
                response.add("id", request.get("id"));
                response.add("jsonrpc", request.get("jsonrpc"));
                try {
                    CLightningJsonObject internalRequest = new CLightningJsonObject(request);
                    // Pre interceptor
                    preInterceptor.forEach(interceptor -> interceptor.doAction(this, internalRequest, result));

                    rpcMethod.doRun(this, internalRequest, result);
                    // Post Interceptor
                    prostInterceptor.forEach(interceptor ->
                            interceptor.doAction(this, internalRequest, result)
                    );
                } catch (CLightningPluginException pluginException) {
                    returnWithAnError(result, pluginException.getCode(), pluginException.getErrorMessage());
                    response.add("error", result.getWrapper());
                    log(PluginLog.ERROR, pluginException.getErrorMessage());
                }
                CLightningLogger.getInstance().debug(TAG, "Plugin result ++++++ " + response + " ++++++");
                if (!response.has("error")) {
                    response.add("result", result.getWrapper());
                }
                CLightningLogger.getInstance().debug(TAG, "******** final answer: " + response.toString());
                stdout.write(response.toString());
                stdout.flush();
            }
        }
    }

    private void returnWithAnError(CLightningJsonObject response, int code, String message) {
        response.add("code", code);
        response.add("message", message);
    }

    public void addSubscription(String event) {
        manifest.addSubscriptions(event);
    }

    public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    public <T> T getParameter(String key) {
        if (key == null || key.isEmpty()) {
            log(PluginLog.ERROR, "Parameter key is null or empty");
            throw new IllegalArgumentException("Key is null or empty");
        }
        if (parameters.containsKey(key)) {
            return (T) parameters.get(key);
        }
        log(PluginLog.WARNING, String.format("Parameter with key %s not found", key));
        return null;
    }

    public boolean hasParameter(String key) {
        if (key == null || key.isEmpty()) {
            log(PluginLog.ERROR, "Parameter key is null or empty");
            throw new IllegalArgumentException("Key is null or empty");
        }
        return parameters.containsKey(key);
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
