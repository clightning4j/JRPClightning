package jrpc.clightning.plugins;

import jrpc.clightning.plugins.rpcmethods.RPCMethod;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningPlugin {

    void start();

    void addRPCMethod(RPCMethod method);

}
