package jrpc.clightning.plugins.interceptor;

import jrpc.clightning.plugins.CLightningPlugin;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

public class CallOnInitMethod implements Interceptor {
  @Override
  public void doAction(
      String methodName,
      CLightningPlugin plugin,
      CLightningJsonObject request,
      CLightningJsonObject response) {
    if (methodName.equals("init")) plugin.onInit(plugin, request, response);
  }
}
