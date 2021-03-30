package jrpc.clightning.plugins;

import java.util.List;
import jrpc.clightning.model.types.CLightingPluginConfig;
import jrpc.clightning.plugins.log.PluginLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

/** @author https://github.com/vincenzopalazzo */
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

  boolean hasParameter(String key);

  boolean hasParametersReady();
}
