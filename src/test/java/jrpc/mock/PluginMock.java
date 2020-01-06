package jrpc.mock;

import jrpc.clightning.plugins.AbstractPlugin;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class PluginMock extends AbstractPlugin {

    @Override
    public void start() {
        super.start();
        LOGGER.debug("******* MOCK plugin method run *******");
    }
}
