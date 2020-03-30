package jrpc.mock;

import jrpc.clightning.plugins.AbstractPlugin;
import jrpc.service.JRPCLightningLogger;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class PluginMock extends AbstractPlugin {

    @Override
    public void start() {
        super.start();
        JRPCLightningLogger.getInstance().debug(TAG,"******* MOCK plugin method run *******");
    }
}
