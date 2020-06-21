package jrpc.mock;

import jrpc.clightning.plugins.AbstractPlugin;
import jrpc.service.JRPCLightningLogger;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class PluginMock extends AbstractPlugin {

    private static final Class TAG = PluginMock.class;

    @Override
    public void start() {
        super.start();
        JRPCLightningLogger.getInstance().debug(TAG,"******* MOCK plugin method run *******");
    }
}
