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
}
