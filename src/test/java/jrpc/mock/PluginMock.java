package jrpc.mock;

import jrpc.clightning.plugins.CLightningPlugin;
import jrpc.service.CLightningLogger;

/** @author https://github.com/vincenzopalazzo */
public class PluginMock extends CLightningPlugin {

  private static final Class TAG = PluginMock.class;

  @Override
  public void start() {
    super.start();
    CLightningLogger.getInstance().debug(TAG, "******* MOCK plugin method run *******");
  }
}
