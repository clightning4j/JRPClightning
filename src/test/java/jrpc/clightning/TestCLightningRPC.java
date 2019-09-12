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

import jrpc.clightning.model.*;
import jrpc.clightning.model.types.AddressType;
import jrpc.clightning.model.types.BitcoinOutput;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Test
    public void testCommandTxPrepareOne(){
        BitcoinOutput bitcoinOutput = new BitcoinOutput("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().txPrepare(bitcoinOutput);
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandWithDrawOne(){
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandTxDiscardOne(){
        ///Before create the tx, with tx prepare
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().withDraw("2NDHWDrq34EEZp77dMxg3qWFsBb8XteV8Yq", "");
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandConnectAndCloseOne(){
        ///Before create the tx, with tx prepare
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().close("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e");
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandFundChannelOne(){
        String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                "10000", "normal", true, 1, new String[]{});
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandFundChannelTwo(){
        String[] addresses = new String[]{"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().fundChannel("03ad9859fcbd6b821f1ee29d6d3c55883a5107588a668bf66dfddc71ca3dad1a4e",
                "10000", "normal", false, 1, new String[]{});
        TestCase.assertNotNull(txBitcoin);
    }

    @Test
    public void testCommandListFunds(){
        CLightningListFounds listFounds = CLightningRPC.getInstance().listFunds();
        TestCase.assertNotNull(listFounds);
    }

    @Test
    public void testCommandConnectOne(){
        String idString = "03ad091993d0ed893a029f94d3c11fe1745e6afdf4582bf7f61e46b9936350771e";
        String port = "9736";
        String id = CLightningRPC.getInstance().connect(idString, "", port);
        TestCase.assertNotNull(id);
    }
}
