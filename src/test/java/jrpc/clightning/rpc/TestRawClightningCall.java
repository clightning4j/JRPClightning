package jrpc.clightning.rpc;

import java.io.IOException;
import junit.framework.TestCase;
import org.junit.Test;

public class TestRawClightningCall extends AbstractTestRPC {

  @Test
  public void testRawCallWithoutParams() {
    try {
      var getInfo = rpc.rawCommand("getinfo");
      var rpcGetInfo = rpc.getInfo();
      TestCase.assertEquals(converter.serialization(rpcGetInfo).trim(), getInfo.trim());
    } catch (IOException e) {
      TestCase.fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void testRawCallException() {
    try {
      rpc.rawCommand("notexist");
      TestCase.fail(
          "This should fail, the notexist method it is not inside the c-lightning implementation");
    } catch (IOException ignored) {
    }
  }
}
