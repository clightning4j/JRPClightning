package jrpc.clightning.plugins;

import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningPlugin {

    void start();

    void addRPCMethod(RPCMethod method);

    List<RPCMethod> getRpcMethods();

    List<String> getSubscriptions();

    List<String> getHooks();

    boolean isDynamic();

    void log(CLightningLevelLog level, String logMessage);

}
