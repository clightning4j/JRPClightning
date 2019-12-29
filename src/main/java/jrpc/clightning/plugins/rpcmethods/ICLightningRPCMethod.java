package jrpc.clightning.plugins.rpcmethods;

import jrpc.clightning.plugins.exceptions.CLightningPluginException;

import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningRPCMethod {

    /**
     * This method implement the core of the rpc method
     */
    void doRun(Object...params);

    String getName();

    String getUsage();

    String getDescription();

    String getLongDescription();
}
