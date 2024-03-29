package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.annotations.Expose;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.log.PluginLog;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

public class RPCMethodReflection extends AbstractRPCMethod {

  private static final Class TAG = RPCMethodReflection.class;

  @Expose private Method method;

  public RPCMethodReflection(
      String name, String usage, String description, String longDescription, Method method) {
    super(name, usage, description, longDescription);
    this.method = method;
  }

  public RPCMethodReflection(
      String name,
      String usage,
      String description,
      String longDescription,
      RPCMethodType type,
      Method method) {
    super(name, usage, description, longDescription, type);
    this.method = method;
  }

  @Override
  public void doRun(
      ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
    try {
      method.invoke(plugin, new Object[] {plugin, request, response});
    } catch (IllegalAccessException | InvocationTargetException e) {
      plugin.log(PluginLog.ERROR, e.getLocalizedMessage());
      e.printStackTrace();
    }
  }
}
