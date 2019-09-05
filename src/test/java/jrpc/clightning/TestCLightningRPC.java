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

import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.types.AddressType;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCLightningRPC {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCLightningRPC.class);

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
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(1000, "Hello this is an test " + Math.random(), "description");
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());
    }

    @Test
    public void testCommandGetInvoiceTwo(){
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(1000, "This is an test " + Math.random(), "description", "1w");
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());
    }

    @Test
    public void testCommandGetInvoiceThree(){
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(100,
                "This is an test " + Math.random(), "description", "1w",
                    new String[]{"2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"}, "",false);
        LOGGER.debug("invoice: " + invoice.getBolt11());
        TestCase.assertNotNull(invoice.getBolt11());
    }

    @Test
    public void testCommandGetListInvoiceOne(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices("");
        TestCase.assertFalse(listInvoices.getListInvoice().isEmpty());
    }

    @Test
    public void testCommandGetListInvoiceTwo(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices();
        TestCase.assertFalse(listInvoices.getListInvoice().isEmpty());
    }

    @Test
    public void testCommandGetListInvoiceThree(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices("Send me payment ");
        TestCase.assertEquals(1, listInvoices.getListInvoice().size());
    }
}
