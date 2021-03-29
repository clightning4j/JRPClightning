package jrpc.util;

import jrpc.clightning.model.CLightningGetInfo;
import junit.framework.TestCase;
import org.junit.Test;

public class TestUtils {

    @Test
    public void testCheckGetNodeOneInfo() {
        CLightningGetInfo info = MocksUtils.getInfoFirstNode();
        TestCase.assertNotNull(info);
    }
}
