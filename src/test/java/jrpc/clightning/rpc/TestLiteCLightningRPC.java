package jrpc.clightning.rpc;

import jrpc.clightning.LiteCLightningRPC;
import jrpc.clightning.model.CLightningGetInfo;
import junit.framework.TestCase;
import org.junit.Test;

public class TestLiteCLightningRPC {
  private LiteCLightningRPC liteRpc = new LiteCLightningRPC();

  @Test
  public void testGetInfoCall() {
    CLightningGetInfo getInfo = liteRpc.call("getinfo", CLightningGetInfo.class);
    TestCase.assertFalse(getInfo.getId().isEmpty());
  }
}
