package jrpc.service;

import jrpc.clightning.service.CLightningConfigurator;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCLightningConfigurator {

    @Test
    public void testConfiguratorNotExceptionOne(){
        String url = CLightningConfigurator.getInstance().getUrl();
        TestCase.assertNotNull(url);
    }

    @Test
    public void testSocketPathValueOne(){
        String socketPath = CLightningConfigurator.getInstance().getSocketPath();
        TestCase.assertNotNull(socketPath);
    }

    @Test
    public void testSocketPathValueTwo(){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/clightning-rpc.properties"));
            String nameRpc = properties.getProperty("RPC_DIR").substring(0, properties.getProperty("RPC_DIR").length() - "lightning-rpc".length() - 1);
            String socketPath = CLightningConfigurator.getInstance().getSocketPath();
            TestCase.assertEquals(nameRpc, socketPath);
        } catch (IOException e) {
            TestCase.fail("Assertion file bacause there is an exception with this message: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void testSocketFileNameValueOne(){
        String socketPath = CLightningConfigurator.getInstance().getSocketFileName();
        TestCase.assertNotNull(socketPath);
    }

    @Test
    public void testSocketFileNameValueTwo(){
        String nameRpc = "lightning-rpc";
        String socketPath = CLightningConfigurator.getInstance().getSocketFileName();
        TestCase.assertEquals(nameRpc, socketPath);
    }
}
