package jrpc.clightning.plugins.interceptor;

import jrpc.clightning.plugins.CLightningPlugin;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

public interface Interceptor {

  void doAction(
      CLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response);
}
