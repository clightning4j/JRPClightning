package jrpc.clightning.plugins;

import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningPlugin {

    void start();

    void addRPCMethod(AbstractRPCMethod method);

    List<AbstractRPCMethod> getRpcMethods();

    List<String> getSubscriptions();

    List<String> getHooks();

    boolean isDynamic();

    void log(CLightningLevelLog level, String logMessage);

    void addParameter(String key, Object value);

    <T> T getParameter(String key);

}
