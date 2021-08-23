package jrpc.clightning.rpc;

import jrpc.clightning.model.CLightningListFunds;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningUtilRPC extends AbstractTestRPC {

  @Test
  public void testSimpleListFunds() {
    CLightningListFunds listFunds = rpc.listFunds();
    TestCase.assertNotNull(listFunds);
    TestCase.assertNotNull(listFunds.getChannels());
    TestCase.assertNotNull(listFunds.getOutputs());
  }
}
