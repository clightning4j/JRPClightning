package jrpc.clightning.rpc;

import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.CLightningBitcoinTx;
import jrpc.clightning.model.CLightningFeeRate;
import jrpc.clightning.model.CLightningFundPSBT;
import jrpc.clightning.model.types.AddressType;
import jrpc.clightning.model.types.FeeRateStyle;
import jrpc.clightning.model.types.bitcoin.BitcoinDestination;
import jrpc.service.CLightningLogger;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningBitcoinRPC extends AbstractTestRPC {

  @Test
  public void testCommandNewAddress() {
    String newAddress = rpc.newAddress(AddressType.BECH32);
    CLightningLogger.getInstance().debug(TAG, "Address: " + newAddress);
    TestCase.assertFalse(newAddress.isEmpty());
  }

  @Test
  public void testCommandTxPrepareOne() {
    BitcoinDestination bitcoinOutput =
        new BitcoinDestination("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
    TestCase.assertNotNull(bitcoinOutput);
  }

  @Test
  public void testCommandTxPrepareTwo() {
    assumeTrue(rpc.listFunds().getOutputs().size() > 0);
    try {
      String address = rpc.newAddress(AddressType.BECH32);
      BitcoinDestination bitcoinOutputOne = new BitcoinDestination(address, "100");
      BitcoinDestination bitcoinOutputTwo = new BitcoinDestination(address, "100");
      BitcoinDestination bitcoinOutputThree = new BitcoinDestination(address, "100");
      List<BitcoinDestination> destinations = new ArrayList<>();
      destinations.add(bitcoinOutputOne);
      destinations.add(bitcoinOutputTwo);
      destinations.add(bitcoinOutputThree);
      CLightningBitcoinTx bitcoinTx = rpc.txPrepare(destinations);
      CLightningBitcoinTx tx = rpc.txDiscard(bitcoinTx.getTxId());
      TestCase.assertNotNull(tx);
      // TestCase.fail();
    } catch (CLightningException ex) {
      // TestCase.assertTrue(ex.getLocalizedMessage().contains("Could not afford"));
      TestCase.fail(ex.getLocalizedMessage());
    }
  }

  @Test
  public void testCommandWithDrawOne() {
    try {
      CLightningBitcoinTx withdraw = rpc.withdraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "10000");
      TestCase.assertNotNull(withdraw);
    } catch (CLightningException ex) {
      TestCase.fail(ex.getLocalizedMessage());
    }
  }

  @Test
  public void testCommandTxDiscardOne() {
    try {
      CLightningBitcoinTx txBitcoin = rpc.withdraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "1000");
      TestCase.assertNotNull(txBitcoin.getTxId());
    } catch (CLightningException ex) {
      TestCase.fail(ex.getLocalizedMessage());
    }
  }

  @Test
  public void testCommandFeeRateOne() {
    CLightningFeeRate feeRate = rpc.feeRate(FeeRateStyle.PERKB);
    TestCase.assertNotNull(feeRate);
  }

  @Test
  public void testFundPSBTOne() {
    try {
      CLightningFundPSBT fundPSBT = rpc.fundPSBT("1000msat", 1, 1);
      TestCase.assertNotNull(fundPSBT);
      TestCase.assertTrue(fundPSBT.getPsbt().length() > 20);
    } catch (CLightningException exception) {
      TestCase.fail(exception.getLocalizedMessage());
    }
  }
}
