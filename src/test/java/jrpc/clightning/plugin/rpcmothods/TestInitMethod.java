package jrpc.clightning.plugin.rpcmothods;

import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestInitMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestManifestMethod.class);

    private ICLightningRPCMethod init;

    @Before
    public void init(){
        init = new InitMethod(Boolean.TRUE);
    }

    @Test
    public void testStringMethodEmptyOne(){
        String jsonResult = init.toString();
        LOGGER.debug("\n" + jsonResult);
        TestCase.assertFalse(jsonResult.contains("init"));
    }
}
