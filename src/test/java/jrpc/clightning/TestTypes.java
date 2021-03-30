package jrpc.clightning;

import jrpc.clightning.model.types.AddressType;
import junit.framework.TestCase;
import org.junit.Test;

public class TestTypes {

  @Test
  public void testAddressesType() {
    String bech32 = AddressType.BECH32.getValue();
    String p2shSegwit = AddressType.P2SH_SEGWIT.getValue();

    TestCase.assertEquals("bech32", bech32);
    TestCase.assertEquals("p2sh-segwit", p2shSegwit);
  }
}
