package jrpc.clightning.plugins;

import jrpc.clightning.model.CLightningListConfigs;
import jrpc.clightning.model.types.CLightingPluginConfig;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.log.PluginLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

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

    void log(PluginLog level, String logMessage);

    void log(PluginLog level, CLightningJsonObject json);

    void log(PluginLog level, Object json);

    void addParameter(String key, Object value);

    void setConfigs(CLightingPluginConfig config);

    CLightingPluginConfig getConfigs();

    <T> T getParameter(String key);

}
