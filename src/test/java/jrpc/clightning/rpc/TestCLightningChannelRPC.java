package jrpc.clightning.rpc;

import jrpc.clightning.model.CLightningListChannels;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningChannelRPC extends AbstractTestRPC {

  @Test
  public void testCommandListChannelsOne() {
    CLightningListChannels channles = rpc.listChannels("", "", "");
    TestCase.assertNotNull(channles);
  }
}
