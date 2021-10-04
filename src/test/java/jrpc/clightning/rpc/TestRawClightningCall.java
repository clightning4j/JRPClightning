package jrpc.clightning.rpc;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.wrapper.response.RPCResponseWrapper;
import junit.framework.TestCase;
import org.junit.Test;

public class TestRawClightningCall extends AbstractTestRPC {

  @Test
  public void testRawCallWithoutParams() {
    try {
      var rawString = rpc.rawCommand("getinfo");
      var getInfo =
          (RPCResponseWrapper<CLightningGetInfo>)
              converter.deserialization(
                  rawString, new TypeToken<RPCResponseWrapper<CLightningGetInfo>>() {}.getType());
      TestCase.assertNull(getInfo.getError());
      var rpcGetInfo = rpc.getInfo();
      TestCase.assertEquals(
          converter.serialization(rpcGetInfo).trim(), converter.serialization(getInfo.getResult()));
    } catch (IOException e) {
      TestCase.fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void testRawCallException() {
    try {
      var rawString = rpc.rawCommand("notexist");
      var getInfo =
          (RPCResponseWrapper<CLightningGetInfo>)
              converter.deserialization(
                  rawString, new TypeToken<RPCResponseWrapper<CLightningGetInfo>>() {}.getType());
      TestCase.assertNull(getInfo.getResult());
    } catch (IOException ignored) {
    }
  }
}
