/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.*;
import jrpc.clightning.model.types.AddressType;
import jrpc.clightning.model.types.BitcoinOutput;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.TextStyle;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCLightningRPC {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCLightningRPC.class);
    private static final String TOKE_JOIN_PAR = "+";

  // @Before
    public void cleanInvoice(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices();
        for(CLightningInvoice invoice : listInvoices.getListInvoice()){
            CLightningRPC.getInstance().delInvoice(invoice.getLabel(), invoice.getStatus());
        }
    }

    @Test
    public void testCommandGetInfo(){
        CLightningGetInfo infoNode = CLightningRPC.getInstance().getInfo();
        String id = infoNode.getId();
        String color = infoNode.getColor();
        System.out.println("id=" + id);
        System.out.println("color=" + color);
        TestCase.assertNotNull(infoNode.getNetwork());
    }

    @Test
    public void testCommandNewAddress(){
        String newAddress = CLightningRPC.getInstance().getNewAddress(AddressType.BECH32);
        LOGGER.debug("Address: " + newAddress);
        TestCase.assertFalse(newAddress.isEmpty());
    }

    @Test
    public void testCommandGetInvoiceOne(){
        String label = "Hello this is an test " + Math.random();
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(1000, label, "description");
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());

        CLightningRPC.getInstance().delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetInvoiceTwo(){
        String label = "This is an test " + Math.random();
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(1000, label, "description", "1w");
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());

        CLightningRPC.getInstance().delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetInvoiceThree(){
        String label = "This is an test " + Math.random();
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(100,
                label, "description", "1w",
                    new String[]{"2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"}, "",false);
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());

        CLightningRPC.getInstance().delInvoice(label, "unpaid");
    }

    @Test
    public void testCommandGetListInvoiceOne(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices("");
        TestCase.assertNotNull(listInvoices.getListInvoice());
    }

    @Test
    public void testCommandGetListInvoiceTwo(){
        String label = "This invice was created for test command listIncovoice";
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(1000, label, "description", "1w");

        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices("");
        TestCase.assertEquals(1, listInvoices.getListInvoice().size());

        CLightningRPC.getInstance().delInvoice(label, "unpaid");
    }

    //@Test
    public void testCommandTxPrepareOne(){
        BitcoinOutput bitcoinOutput = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().txPrepare(bitcoinOutput);
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandWithDrawOne(){

        try{
            CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }

    }

    @Test
    public void testCommandTxDiscardOne(){
        ///Before create the tx, with tx prepare
        try{
            CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandConnectAndCloseOne(){
        ///Before create the tx, with tx prepare
        try{
            CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().close("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e");
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandFundChannelOne(){
        try{
            String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
            CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                    "10000", "normal", true, 1, new String[]{});
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test
    public void testCommandFundChannelTwo(){
        try{
            String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
            CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                    "10000", "normal", false, 1, new String[]{});
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }

    }

    @Test
    public void testCommandListFunds(){
        CLightningListFounds listFounds = CLightningRPC.getInstance().listFunds();
        TestCase.assertNotNull(listFounds);
    }

    @Test
    public void testCommandConnectOne(){
        String idString = "03ef7709d8e478d833c73ed78b65dc442f0f64e43a9aa62bc16ab5a835d44fe4ef";
        String port = "19736";
        String id = CLightningRPC.getInstance().connect(idString, "", port);
        TestCase.assertNotNull(id);
    }

    @Test
    public void testCommandPayOne(){
        try{
            String bolt11 = "lntb120p1pwc8ep9pp5t330eyge3p2eukenek7wkzspny8jzt07csxjx3a8hyczzqrk63yqdqdwdjxzumywdskgxqyjw5qcqp28yycdhumpzy8avt4g4crawc7hc5xhdq04tnlqnh458ywvpy0wxp96rhws063g6jr68x3cldckf3s56ynj2q8y2fmnms8khhpvah8s6sqwh4m3e";
            CLightningPay pay = CLightningRPC.getInstance().pay(bolt11);
            TestCase.fail();
        }catch (CLightningException ex){
            TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testCommandPayOTwo(){
        String bolt11 = "";
        CLightningPay pay = CLightningRPC.getInstance().pay(bolt11);
        TestCase.assertNotNull(pay);
    }

    @Test
    public void testCommandListSendPaysOTwo(){
        CLightningListSendPays pay = CLightningRPC.getInstance().listSendPays();
        TestCase.assertNotNull(pay);
    }

    @Test
    public void testCommandListChannelsOne(){
        CLightningListChannels channles = CLightningRPC.getInstance().listChannels("", "");
        TestCase.assertNotNull(channles);
    }

    @Test
    public void testCommandListPeersOne(){
        CLightningListPeers peers = CLightningRPC.getInstance().listPeers("", "");
        TestCase.assertNotNull(peers);
    }

    @Test
    public void testCommandDecodePayOne(){
        String bolt11 = "lntb133n1pw6xee8pp5lejvtfa9vutxh4yxeavznv9fzk30ghcn32pmee8jrgtgzjqp7ytsdr4235x" +
                "jueqd9h8vmmfvdjjq6tnyp3hyetpw3jkggr0dek8jgrxdaezqar9wd6xjmn8yp3k7mtdv9hxggry" +
                "v43k7er92pshjgrfdcs8g6r9ypmhyctswpjhyxqyjw5qcqp2f9qswccpctzw2ya82w83rqkkr0d8rvayctcl7" +
                "5dh6z4q85uqp0jseflzjfap5fajwa35m6ughfrq69l96ur37jgrl63s655es88htygqughf82";
        CLightningDecodePay decodePay = CLightningRPC.getInstance().decodePay(bolt11);
        TestCase.assertNotNull(decodePay);
        TestCase.assertEquals("13300msat", decodePay.getAmountMSat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommandDecodePayTwo(){
        String bolt11 = "";
        CLightningDecodePay decodePay = CLightningRPC.getInstance().decodePay(bolt11);
        TestCase.assertNotNull(decodePay);
        TestCase.assertEquals("13300msat", decodePay.getAmountMSat());
    }
}
