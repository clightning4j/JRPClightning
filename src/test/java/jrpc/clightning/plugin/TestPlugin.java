package jrpc.clightning.plugin;

import jrpc.mock.PluginMock;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPlugin.class);

    private PluginMock pluginMock;

    @Before
    public void init(){
        pluginMock = new PluginMock();
    }

    @Test
    public void testJsonResponseOne(){
        String response = pluginMock.toString();
        LOGGER.debug("\n" + response);
        TestCase.assertFalse(response.contains("init"));
        TestCase.assertFalse(response.contains("getmanifest"));
    }
}
