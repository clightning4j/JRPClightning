package jrpc.clightning.plugin.rpcmothods;

import jrpc.clightning.plugins.AbstractPlugin;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.mock.PluginMock;
import jrpc.mock.RPCMockMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestManifestMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestManifestMethod.class);

    private ICLightningRPCMethod manifest;

    @Before
    public void init(){
        ICLightningPlugin plugin = new PluginMock();
        manifest = new ManifestMethod(plugin);
    }

    @Test
    public void testStringMethodEmptyOne(){
        ManifestMethod method = (ManifestMethod) manifest;
        String jsonResult = method.toString();
        LOGGER.debug("\n" + jsonResult);
        TestCase.assertFalse(jsonResult.contains("usage"));
    }

    @Test
    public void testStringMethodEmptyTwo(){
        ManifestMethod method = (ManifestMethod) manifest;
        String jsonResult = method.toString();
        LOGGER.debug("\n" + jsonResult);
        TestCase.assertFalse(jsonResult.contains("name"));
    }

    @Test
    public void testFilterRPCMethodOne(){
        ManifestMethod method = (ManifestMethod) manifest;
        List<RPCMethod> methodList = new ArrayList<>();
        methodList.add(method);
        methodList.add(method);
        method.addMethods(methodList);
        TestCase.assertTrue(method.getRpcMethods().isEmpty());
    }

    @Test
    public void testFilterRPCMethodTwo(){
        ManifestMethod method = (ManifestMethod) manifest;
        List<RPCMethod> methodList = new ArrayList<>();
        methodList.add(method);
        methodList.add(new RPCMockMethod("mock", "mock", "mock"));
        method.addMethods(methodList);
        TestCase.assertFalse(method.getRpcMethods().isEmpty());
        TestCase.assertEquals(1, method.getRpcMethods().size());
    }

}
