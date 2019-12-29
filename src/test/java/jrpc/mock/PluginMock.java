package jrpc.mock;

import jrpc.clightning.plugins.AbstractPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class PluginMock extends AbstractPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginMock.class);

    @Override
    public void start() {
        LOGGER.debug("******* MOCK plugin method run *******");
    }
}
