package jrpc.clightning.rpc;

import jrpc.clightning.model.CLightningListFounds;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningUtilRPC extends AbstractTestRPC {

  @Test
  public void testSimpleListFounds() {
    CLightningListFounds listFounds = rpc.listFunds();
    TestCase.assertNotNull(listFounds);
    TestCase.assertNotNull(listFounds.getChannels());
    TestCase.assertNotNull(listFounds.getOutputs());
  }
}
