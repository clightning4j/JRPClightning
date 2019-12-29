package jrpc.service;

import jrpc.clightning.service.CLightningConfigurator;
import junit.framework.TestCase;
import org.junit.Test;

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
        //TODO add this path to proprietis files
        String nameRpc = "/media/vincenzo/Maxtor/sanboxTestWrapperRPC/lightning_dir_one";
        String socketPath = CLightningConfigurator.getInstance().getSocketPath();
        TestCase.assertEquals(nameRpc, socketPath);
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
