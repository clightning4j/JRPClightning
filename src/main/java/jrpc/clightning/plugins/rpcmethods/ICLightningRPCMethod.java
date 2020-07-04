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

    /**
     * This method is deprecated in fovor of doRun(jsonRequest, jsonResponse)
     */
    @Deprecated
    String doRun(Object...params);

    @Deprecated
    void doRun(CLightningJsonObject request, CLightningJsonObject response);

    void doRun(ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response);

    String getName();

    String getUsage();

    String getDescription();

    String getLongDescription();
}
