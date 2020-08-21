package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.service.CLightningLogger;
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
        CLightningLogger.getInstance().debug(TAG, "***** Config propriety " + config.toString() + " *****");
        String rpcPath = config.get("lightning-dir").getAsString() + "/" + config.get("rpc-file").getAsString();
        CLightningLogger.getInstance().debug(TAG, "***** Method init rpc file path " + rpcPath + " *****");
        //This method change the url inside the configurator to set the personal path from plugin.
        CLightningConfigurator.getInstance().changeUrlRpcFile(rpcPath);

        plugin.log(CLightningLevelLog.WARNING, jsonParams.toString());
        mappingParameters(plugin, jsonParams);
    }

    private void mappingParameters(ICLightningPlugin plugin, JsonObject jsonParams) {
        if(jsonParams == null){
            throw new IllegalArgumentException("jsonParams null");
        }
        if(jsonParams.has("options")){
            JsonObject options = jsonParams.getAsJsonObject("options");
            options.keySet().forEach(key ->{
                JsonPrimitive value = options.get(key).getAsJsonPrimitive();
                plugin.log(CLightningLevelLog.WARNING,value.toString());
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
