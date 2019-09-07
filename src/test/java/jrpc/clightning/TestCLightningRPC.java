/**
 * Copyright 2019 https://github.com/vincenzopalazzo
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

import jrpc.clightning.model.CLightningBitcoinTx;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.types.AddressType;
import jrpc.clightning.model.types.BitcoinOutput;
import junit.framework.TestCase;
import org.junit.Before;
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
        TestCase.assertEquals(infoNode.getNetwork(), "testnet");
    }

    @Test
    public void testCommandNewAddress(){
        String newAddress = CLightningRPC.getInstance().getNewAddress(AddressType.BECH32);
        LOGGER.debug("Address: " + newAddress);
        TestCase.assertTrue(newAddress.contains("tb"));
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
    public void testCommandCloseOne(){
        ///Before create the tx, with tx prepare
        CLightningBitcoinTx txBitcoin = CLightningRPC.getInstance().close("02f6725f9c1c40333b67faea92fd211c183050f28df32cac3f9d69685fe9665432");
        TestCase.assertNotNull(txBitcoin);
    }
}
