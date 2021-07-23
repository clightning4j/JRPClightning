package jrpc.clightning.rpc;

import jrpc.clightning.model.CLightningListFunds;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningUtilRPC extends AbstractTestRPC {

  @Test
  public void testSimpleListFounds() {
    CLightningListFunds listFounds = rpc.listFunds();
    TestCase.assertNotNull(listFounds);
    TestCase.assertNotNull(listFounds.getChannels());
    TestCase.assertNotNull(listFounds.getOutputs());
  }
}
