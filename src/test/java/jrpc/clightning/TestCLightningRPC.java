/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.model.*;
import jrpc.clightning.model.CLightningPayResult;
import jrpc.clightning.model.types.*;
import jrpc.clightning.model.types.bitcoin.BitcoinOutput;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.mock.rpccommand.CustomCommand;
import jrpc.mock.rpccommand.PersonalDelPayRPCCommand;
import jrpc.service.CLightningLogger;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCLightningRPC {

    private static final Class TAG = TestCLightningRPC.class;

    CLightningRPC rpc = CLightningRPC.getInstance();

    @Before
    public void cleanAll() {
        CLightningListInvoices listInvoices = rpc.listInvoices();
        if (!listInvoices.getListInvoice().isEmpty()) {
            for (CLightningInvoice invoice : listInvoices.getListInvoice()) {
                rpc.delInvoice(invoice.getLabel(), invoice.getStatus());
            }
        }
    }

    @Test
    public void testCommandGetInfo() {
        CLightningGetInfo infoNode = rpc.getInfo();
        String id = infoNode.getId();
        String color = infoNode.getColor();
        System.out.println("id=" + id);
        System.out.println("color=" + color);
        TestCase.assertNotNull(infoNode.getNetwork());
    }

    @Test
    public void testCommandNewAddress() {
        String newAddress = rpc.getNewAddress(AddressType.BECH32);
        CLightningLogger.getInstance().debug(TAG, "Address: " + newAddress);
        TestCase.assertFalse(newAddress.isEmpty());
    }

    @Test
    public void testCommandGetInvoiceOne() {
        String label = "Hello this is an test " + Math.random();
        CLightningInvoice invoice = rpc.invoice("1000", label, "description");
        CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());
        rpc.delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetInvoiceTwo() {
        String label = "This is an test " + Math.random();
        CLightningInvoice invoice = rpc.invoice("1000", label, "description");
        CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());

        rpc.delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetInvoiceThree() {
        String label = "This is an test " + Math.random();
        CLightningInvoice invoice = rpc.invoice("100",
                label, "description", "1w",
                new String[]{"2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"}, "", false);
        CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());
        rpc.delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetInvoiceFour() {
        String label = "Hello this is an test " + Math.random();
        CLightningInvoice invoice = rpc.invoice("1000", label, "description");
        TestCase.assertNotNull(invoice.getBolt11());
        TestCase.assertNotNull(invoice.getStatus());
        TestCase.assertNotNull(invoice.getmSatoshi());
        TestCase.assertNotNull(invoice.getBolt11());
        TestCase.assertNotNull(invoice.getExpiresAt());
        TestCase.assertNotNull(invoice.getDescription());
        TestCase.assertNotNull(invoice.getPaymentHash());
        rpc.delInvoice(label, invoice.getStatus());
    }

    @Test
    public void testCommandGetListInvoiceOne() {
        CLightningListInvoices listInvoices = rpc.listInvoices("");
        TestCase.assertNotNull(listInvoices.getListInvoice());
    }

    @Test
    public void testCommandGetListInvoiceTwo() {
        String label = "This invice was created for test command listIncovoice";
        CLightningInvoice invoice = rpc
                .invoice("1000", label, "description");
        TestCase.assertNotNull(invoice);
        CLightningListInvoices listInvoices = rpc.listInvoices("");
        TestCase.assertEquals(1, listInvoices.getListInvoice().size());
        rpc.delInvoice(label, "unpaid");

    }

    @Test
    public void testCommandTxPrepareOne() {
        BitcoinOutput bitcoinOutput = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
        TestCase.assertNotNull(bitcoinOutput);
    }

    @Test
    public void testCommandTxPrepareTwo() {
        try {
            BitcoinOutput bitcoinOutputOne = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "100");
            BitcoinOutput bitcoinOutputTwo = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "100");
            BitcoinOutput bitcoinOutputThree = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "100");
            CLightningBitcoinTx bitcoinTx = rpc.txPrepare(bitcoinOutputOne, bitcoinOutputTwo, bitcoinOutputThree);
            rpc.txDiscard(bitcoinTx.getTxId());
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getLocalizedMessage().contains("this destination wants all satoshi."));
        }
    }

    @Test
    public void testCommandWithDrawOne() {
        try {
            rpc.withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandTxDiscardOne() {
        try {
            CLightningBitcoinTx txBitcoin = rpc.withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
            TestCase.assertNotNull(txBitcoin.getTxId());
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandConnectAndCloseOne() {
        try {
            CLightningBitcoinTx txBitcoin = rpc.close("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e");
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandFundChannelOne() {
        try {
            String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
            CLightningBitcoinTx txBitcoin = rpc.fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                    "10000", "normal", true, 1, new String[]{});
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandFundChannelTwo() {
        try {
            String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
            CLightningBitcoinTx txBitcoin = rpc.fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                    "10000", "normal", false, 1, new String[]{});
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }

    }

    @Test
    public void testCommandListFunds() {
        CLightningListFounds listFounds = rpc.listFunds();
        TestCase.assertNotNull(listFounds);
    }

    @Test
    public void testCommandConnectOne() {
        String idString = "02bd7e87692775d2ce65a3cf81765d942ea0b15c883c6bd6d060005aaa43dc5cc6";
        String port = "19736";
        String id = rpc.connect(idString, "", port).getId();
        TestCase.assertNotNull(id);
    }

    @Test
    public void testCommandPayOne() {
        try {
            String bolt11 = "lntb120p1pwc8ep9pp5t330eyge3p2eukenek7wkzspny8jzt07csxjx3a8hyczzqrk63yqdqdwdjxzumywdskgxqyjw5qcqp28yycdhumpzy8avt4g4crawc7hc5xhdq04tnlqnh458ywvpy0wxp96rhws063g6jr68x3cldckf3s56ynj2q8y2fmnms8khhpvah8s6sqwh4m3e";
            CLightningPayResult pay = rpc.pay(bolt11);
            TestCase.fail();
        } catch (CLightningException ex) {
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testCommandPayOTwo() {
        String bolt11 = "";
        CLightningPayResult pay = rpc.pay(bolt11);
        TestCase.assertNotNull(pay);
    }

    @Test
    public void testCommandListSendPaysOTwo() {
        CLightningListSendPays pays = rpc.listSendPays();
        TestCase.assertNotNull(pays);
    }

    @Test
    public void testCommandListChannelsOne() {
        CLightningListChannels channles = rpc.listChannels("", "");
        TestCase.assertNotNull(channles);
    }

    @Test
    public void testCommandListPeersOne() {
        CLightningListPeers peers = rpc.listPeers("", "");
        TestCase.assertNotNull(peers);
    }

    @Test
    public void testCommandDecodePayOne() {
        String bolt11 = "lntb133n1pw6xee8pp5lejvtfa9vutxh4yxeavznv9fzk30ghcn32pmee8jrgtgzjqp7ytsdr4235x" +
                "jueqd9h8vmmfvdjjq6tnyp3hyetpw3jkggr0dek8jgrxdaezqar9wd6xjmn8yp3k7mtdv9hxggry" +
                "v43k7er92pshjgrfdcs8g6r9ypmhyctswpjhyxqyjw5qcqp2f9qswccpctzw2ya82w83rqkkr0d8rvayctcl7" +
                "5dh6z4q85uqp0jseflzjfap5fajwa35m6ughfrq69l96ur37jgrl63s655es88htygqughf82";
        CLightningDecodePay decodePay = rpc.decodePay(bolt11);
        TestCase.assertNotNull(decodePay);
        TestCase.assertEquals("13300msat", decodePay.getAmountMSat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommandDecodePayTwo() {
        String bolt11 = "";
        CLightningDecodePay decodePay = rpc.decodePay(bolt11);
        TestCase.assertNotNull(decodePay);
        TestCase.assertEquals("13300msat", decodePay.getAmountMSat());
    }

    @Test
    public void testCommandFeeRateOne() {
        CLightningFeeRate feeRate = rpc.feeRate(FeeRateStyle.PERKB);
        TestCase.assertNotNull(feeRate);
    }

    @Test
    public void testGetRouteOne() {
        try {
            rpc.getRoute("0222432c04c91358a1347d7ecefd846204355bd335145d3816301228a9464057e6",
                    "2000", 0f);
            TestCase.fail();
        } catch (CLightningException exception) {
            exception.printStackTrace();
        }
    }

    @Test(expected = CLightningException.class)
    public void testDisconnectOne() {
        rpc.disconnect("");
    }

    @Test
    public void testDisconnectTwo() throws InterruptedException {
        String nodeId = "0222432c04c91358a1347d7ecefd846204355bd335145d3816301228a9464057e6";
        rpc.connect(nodeId, "", "19735");
        Thread.sleep(2000);
        boolean disconnected = rpc.disconnect(nodeId);
        TestCase.assertTrue(disconnected);
    }

    @Test
    public void testListPaysOne() {
        CLightningListPays listPays = rpc.listPays();
        TestCase.assertNotNull(listPays.getPays());
    }

    @Test
    public void testListSendPaymentsOne() {
        CLightningListSendPays payments = rpc.listSendPays();
        TestCase.assertNotNull(payments);
    }

    @Test
    public void testListNodesOne() {
        CLightningListNodes listNodes = rpc.listNodes();
        TestCase.assertNotNull(listNodes);
    }

    @Test
    public void testListNodesTwo() {
        //Connect to node one
        String nodeId = "0222432c04c91358a1347d7ecefd846204355bd335145d3816301228a9464057e6";
        rpc.connect(nodeId, "", "19735");

        CLightningListNodes listNodes = rpc.listNodes(nodeId);
        TestCase.assertEquals(1, listNodes.getNodes().size());

        rpc.disconnect(nodeId, true);
    }

    @Test
    public void testPingOne() {
        String nodeId = "0222432c04c91358a1347d7ecefd846204355bd335145d3816301228a9464057e6";
        rpc.connect(nodeId, "", "19735");

        CLightningPing pingResult = rpc.ping(nodeId);
        TestCase.assertNotNull(pingResult);

        rpc.disconnect(nodeId);
    }

    @Test
    public void testListTransactionsOne() {
        CLightningListTransactions listTransactions = rpc.listTransactions();
        TestCase.assertNotNull(listTransactions);
    }

    @Test
    public void testHelpOne() {
        CLightningHelp help = rpc.help();
        TestCase.assertNotNull(help);
    }

    @Test
    public void testFundPSBTOne() {
        try{
            CLightningFundPSBT fundPSBT = rpc.fundPSBT("1000msat", 1, 1);
            TestCase.assertNotNull(fundPSBT);
            TestCase.assertTrue(fundPSBT.getPsbt().length() > 20);
        }catch (CLightningException exception){
            TestCase.assertTrue(exception.getMessage().contains("Could not afford"));
        }
    }

    @Test
    public void testListConfigs(){
        CLightningListConfigs configs = rpc.listConfigs();
        TestCase.assertNotNull(configs);
        TestCase.assertEquals("regtest", configs.getNetwork());
        TestCase.assertEquals(CLightningConfigurator.getInstance().getSocketPath(),
                configs.getLightningDir() + "/" + configs.getNetwork());
    }

    //This command is available inside the lightning project
    // from version 0.9.1
    @Test(expected = CommandException.class)
    public void testCustomCommandDelPay() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("payment_hash", "YOUR_BOLT11");
        PersonalDelPayRPCCommand paysCommand = new PersonalDelPayRPCCommand();
        rpc.registerCommand(CustomCommand.DELPAY, paysCommand);

        CLightningListPays result = rpc.runRegisterCommand(CustomCommand.DELPAY, payload);
        TestCase.assertNotNull(result);
    }

    @Test(expected = CommandException.class)
    public void testCustomCommandDelPayWithAnnotation() {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("payment_hash", "YOUR_BOLT11");

        CLightningListPays result = rpc.runRegisterCommand("delpay", payload);
        TestCase.assertNotNull(result);
    }
}
