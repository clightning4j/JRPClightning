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

import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCommandRPCMediator {

    private static final String JOIN_TOKEN_PROP = "+";

    private CommandRPCMediator mediator;

    @Before
    public void configure(){
        try {
            mediator = new CommandRPCMediator(new CLightningSocket());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        //Clean invoice
        //cleanInvoice();
    }

    private void cleanInvoice(){
        CLightningListInvoices listInvoices = CLightningRPC.getInstance().getListInvoices();
        for(CLightningInvoice invoice : listInvoices.getListInvoice()){
            CLightningRPC.getInstance().delInvoice(invoice.getLabel(), invoice.getStatus());
        }
    }

    @Test
    public void testParsingPayloadOne(){
        String payload = "";
        Object response = mediator.runCommand(Command.GETINFO, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadTwo(){
        String payload = "foo";
        CLightningGetInfo response = (CLightningGetInfo) mediator.runCommand(Command.GETINFO, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadThree(){
        String payload = "pippo=pippo";
        CLightningGetInfo response = (CLightningGetInfo) mediator.runCommand(Command.GETINFO, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadAddressTypeBech32(){
        String payload = "addresstype=bech32";
        Object response = mediator.runCommand(Command.NEWADDR, payload);
        TestCase.assertNotNull(response);
    }

    //p2sh-segwit
    @Test
    public void testParsingPayloadAddressTypeP2SH(){
        String payload = "addresstype=p2sh-segwit";
        Object response = mediator.runCommand(Command.NEWADDR, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadInvoiceOne(){
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000" + JOIN_TOKEN_PROP + "label=" + label + JOIN_TOKEN_PROP + "description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);

        String status = "unpaid";
        payload = "label=" + label + JOIN_TOKEN_PROP + "status=" + status;
        response = mediator.runCommand(Command.DELINVOICE, payload);

        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceTwo(){
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000" + JOIN_TOKEN_PROP +"description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceThree(){
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000" + JOIN_TOKEN_PROP +"label" + JOIN_TOKEN_PROP + "description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadInvoiceFour(){
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000" + JOIN_TOKEN_PROP +"label=" + label + JOIN_TOKEN_PROP + "description=" + description + JOIN_TOKEN_PROP + "expiry=1w";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);

        String status = "unpaid";
        payload = "label=" + label + JOIN_TOKEN_PROP + "status=" + status;
        response = mediator.runCommand(Command.DELINVOICE, payload);

        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceFive(){
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);;
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000"+ JOIN_TOKEN_PROP +"label=" + label + JOIN_TOKEN_PROP + "description=" + description + JOIN_TOKEN_PROP + "expiry=1p";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test //TODO test invoice on the propriety fallbacks, preimage, exposeprivatechannels
    public void testParsingPayloadDelInvoiceOne(){
        String label = "This invoice is created for test the command delete invoice";
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000"+ JOIN_TOKEN_PROP + "label=" + label+ JOIN_TOKEN_PROP + "description=" + description + JOIN_TOKEN_PROP + "expiry=1w";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);

        String status = "unpaid";
        payload = "label=" + label + JOIN_TOKEN_PROP + "status=" + status;
        response = mediator.runCommand(Command.DELINVOICE, payload);

        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadDelInvoiceTwo(){
        String label = " ";
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000"+ JOIN_TOKEN_PROP +"label=" + label + JOIN_TOKEN_PROP + "description=" + description + JOIN_TOKEN_PROP + "expiry=1w";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);

        String status = "unpaid";
        payload = "label=" + label + JOIN_TOKEN_PROP + "status=" + status;
        response = mediator.runCommand(Command.DELINVOICE, payload);

        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadDelInvoiceThree(){
        String status = "pippo";
        String payload = "label=" + " " + JOIN_TOKEN_PROP + "status=" + status;
        mediator.runCommand(Command.DELINVOICE, payload);
    }

    @Test
    public void testParsingPayloadAutoCleanInvoiceOne(){
        String payload = " ";
        Object response = mediator.runCommand(Command.AUTOCLEANINVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadAutoCleanInvoiceTwo(){
        String cycleSeconds = "3600";
        String expiredBy = "86400";
        String payload = "cycle_seconds=" + cycleSeconds + JOIN_TOKEN_PROP + "expired_by=" + expiredBy;
        Object response = mediator.runCommand(Command.AUTOCLEANINVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadAutoCleanInvoiceThree(){
        String cycleSeconds = "3600";
        String payload = "cycle_seconds=" + cycleSeconds;
        Object response = mediator.runCommand(Command.AUTOCLEANINVOICE, payload);
        TestCase.assertNotNull(response);
    }

   @Test
    public void testParsingPayloadTxPrepareOne(){
        String cycleSeconds = "3600";
        String payload = "cycle_seconds=" + cycleSeconds;
        Object response = mediator.runCommand(Command.AUTOCLEANINVOICE, payload);
        TestCase.assertNotNull(response);
    }
}
