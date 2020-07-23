package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.JsonObject;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningRPCMethod {

    void doRun(ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response);

    String getName();

    String getUsage();

    String getDescription();

    String getLongDescription();

    RPCMethodType getType();
}
