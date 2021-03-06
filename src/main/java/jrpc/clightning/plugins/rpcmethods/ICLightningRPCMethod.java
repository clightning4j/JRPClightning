package jrpc.clightning.plugins.rpcmethods;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningRPCMethod {

    void doRun(ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response)
            throws CLightningException;

    String getName();

    String getUsage();

    String getDescription();

    String getLongDescription();

    RPCMethodType getType();
}
