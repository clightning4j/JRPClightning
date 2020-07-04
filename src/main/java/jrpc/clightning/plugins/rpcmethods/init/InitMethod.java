package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.JsonObject;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class InitMethod extends RPCMethod {

    private static final Class TAG = InitMethod.class;

    public InitMethod() {
        super("init", null, null);
    }

    /**
     * This method is used to receive the information on c-lightning node to configure the plugin
     */
    @Override
    public String doRun(Object... params) {
        CLightningLogger.getInstance().debug(TAG, "**** Called rpc method init ****");
        if (params.length == 1) {
            Object object = params[0];
            if (object instanceof JsonObject) {
                JsonObject initRequest = (JsonObject) object;
                CLightningLogger.getInstance().debug(TAG, "***** Json Object  " + initRequest.toString() + " *****");
                JsonObject jsonParams = (JsonObject) initRequest.get("params");
                JsonObject config = (JsonObject) jsonParams.get("configuration");
                CLightningLogger.getInstance().debug(TAG, "***** Config propriety " + config.toString() + " *****");
                String rpcPath = config.get("lightning-dir").getAsString() + "/" + config.get("rpc-file").getAsString();
                CLightningLogger.getInstance().debug(TAG, "***** Method init rpc file path " + rpcPath + " *****");
                CLightningConfigurator.getInstance().changeUrlRpcFile(rpcPath);
                //This method change the url inside the configurator to set the personal path from plugin.
                //also this method ignore the variable startup, maybe I should be save this variable inside the mediator?
            }
        }
        return new JsonConverter().serialization(this);
    }

    @Override
    public void doRun(CLightningJsonObject request, CLightningJsonObject response) {
        //TODO deprecaded, I can delete this method inside the interfaces
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
        CLightningConfigurator.getInstance().changeUrlRpcFile(rpcPath);
        //This method change the url inside the configurator to set the personal path from plugin.
        //also this method ignore the variable startup, maybe I should be save this variable inside the mediator?
    }
}
