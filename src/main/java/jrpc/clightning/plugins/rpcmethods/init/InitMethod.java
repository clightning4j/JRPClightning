package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import jrpc.clightning.model.types.CLightingPluginConfig;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class InitMethod extends AbstractRPCMethod {

    private static final Class TAG = InitMethod.class;

    public InitMethod() {
        super("init", null, null);
    }

    @Override
    public void doRun(ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
        CLightningLogger.getInstance().debug(TAG, "**** Called rpc method init ****");
        CLightningLogger.getInstance().debug(TAG, "***** Json Object  " + request.toString() + " *****");
        JsonObject jsonParams = (JsonObject) request.get("params");
        JsonObject config = (JsonObject) jsonParams.get("configuration");
        JsonConverter converter = new JsonConverter();
        CLightingPluginConfig pluginConf = (CLightingPluginConfig) converter.deserialization(
                converter.serialization(config), CLightingPluginConfig.class);
        plugin.setConfigs(pluginConf);
        String rpcPath = pluginConf.getLightningDir() + "/" + pluginConf.getRpcFile();
        //This method change the url inside the configurator to set the personal path from plugin.
        CLightningConfigurator.getInstance().changeUrlRpcFile(rpcPath);
        mappingParameters(plugin, jsonParams);
    }

    //TODO I can bing this parameter with the direct propriety annotated!
    private void mappingParameters(ICLightningPlugin plugin, JsonObject jsonParams) {
        if(jsonParams == null){
            throw new IllegalArgumentException("jsonParams null");
        }
        if(jsonParams.has("options")){
            JsonObject options = jsonParams.getAsJsonObject("options");
            options.keySet().forEach(key -> {
                JsonPrimitive value = options.get(key).getAsJsonPrimitive();
                if(value.isBoolean()){
                    plugin.addParameter(key, value.getAsBoolean());
                }else if(value.isNumber()){
                    plugin.addParameter(key, value.getAsInt());
                }else{
                    plugin.addParameter(key, value.getAsString());
                }
            });
        }
    }
}
