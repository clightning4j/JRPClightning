package jrpc.clightning.rpc;

import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.model.CLightningBitcoinTx;
import jrpc.clightning.model.CLightningListChannels;
import jrpc.clightning.model.CLightningListNodes;
import jrpc.clightning.model.CLightningListPeers;
import jrpc.clightning.model.types.CLightningOutput;
import jrpc.clightning.model.types.CLightningPing;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;
import jrpc.service.CLightningLogger;
import jrpc.util.MocksUtils;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCLightningNetworkRPC extends AbstractTestRPC {

  @Test
  public void testCommandConnectAndCloseOne() throws InterruptedException {
    assumeTrue(rpc.listFunds().getOutputs().size() > 0);
    CLightningLogger.getInstance()
        .debug(TAG, "invoice: " + converter.serialization(rpc.listFunds()));
    try {
      CLightningOutput output = rpc.listFunds().getOutputs().get(0);
      BitcoinUTXO utxo = new BitcoinUTXO(output.getTxId(), output.getOutput());
      List<BitcoinUTXO> utxos = new ArrayList<>();
      utxos.add(utxo);
      rpc.connect(
          infoFirstNode.getId(),
          infoFirstNode.getBinding().get(0).getAddress(),
          infoFirstNode.getBinding().get(0).getPort() + "");
      CLightningBitcoinTx fundTx =
          rpc.fundChannel(infoFirstNode.getId(), "50000", "urgent", true, 1, utxos, "", "");
      TestCase.assertNotNull(fundTx.getTxId());
      MocksUtils.generateBlockBitcoin();
      Thread.sleep(2000);
      CLightningListChannels channels = rpc.listChannels();
      assumeTrue(!channels.getChannels().isEmpty());
      var closeTx = rpc.close(channels.getChannels().get(0).getShortChannelId());
      TestCase.assertNotNull(closeTx.getTxId());
      TestCase.assertNotNull(closeTx.getTx());
    } catch (CLightningException | CommandException ex) {
      TestCase.fail(
          ex.getLocalizedMessage()
              + " \n listfunds -> "
              + converter.serialization(rpc.listFunds()));
    }
  }

  @Test
  public void testCommandConnectOne() {
    String idString = infoFirstNode.getId();
    String port = "19735";
    String id = rpc.connect(idString, "", port).getId();
    TestCase.assertNotNull(id);
  }

  @Test
  public void testCommandListPeersOne() {
    CLightningListPeers peers = rpc.listPeers("", "");
    TestCase.assertNotNull(peers);
  }

  @Test
  public void testGetRouteOne() {
    try {
      rpc.getRoute(infoFirstNode.getId(), "2000", 0f);
      TestCase.fail();
    } catch (CLightningException exception) {
      TestCase.assertTrue(
          exception.getLocalizedMessage().contains("unknown source node_id (no public channels?)"));
    }
  }

  @Test(expected = CLightningException.class)
  public void testDisconnectOne() {
    rpc.disconnect("");
  }

  @Test
  public void testDisconnectTwo() throws InterruptedException {
    String nodeId = infoFirstNode.getId();
    rpc.connect(nodeId, "", "19735");
    Thread.sleep(2000);
    boolean disconnected = rpc.disconnect(nodeId);
    TestCase.assertTrue(disconnected);
  }

  @Test
  public void testListNodesOne() {
    CLightningListNodes listNodes = rpc.listNodes();
    TestCase.assertNotNull(listNodes);
  }

  @Test
  public void testListNodesTwo() {
    // Connect to node one
    String nodeId = infoFirstNode.getId();
    CLightningListNodes listNodes = rpc.listNodes(nodeId);
    TestCase.assertEquals(0, listNodes.getNodes().size());
  }

  @Test
  public void testPingOne() {
    String nodeId = infoFirstNode.getId();
    rpc.connect(nodeId, "", "19735");

    CLightningPing pingResult = rpc.ping(nodeId);
    TestCase.assertNotNull(pingResult);

    rpc.disconnect(nodeId);
  }
}
