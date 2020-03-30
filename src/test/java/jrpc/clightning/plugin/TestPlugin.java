package jrpc.clightning.plugin;

import jrpc.mock.PluginMock;
import jrpc.mock.RPCMockMethod;
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
        RPCMockMethod method = new RPCMockMethod("test", "", "this is a simple test");
        pluginMock.addRPCMethod(method);

        pluginMock.start();

        String response = pluginMock.toString();
        LOGGER.debug("\n" + response);
        TestCase.assertFalse(response.contains("init"));
        TestCase.assertFalse(response.contains("getmanifest"));
        TestCase.assertFalse(response.contains("null"));
    }
}
