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

import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.model.CLightningGetInfo;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestCommandRPCMediator {

    private CommandRPCMediator mediator;

    @Before
    public void configure(){
        mediator = new CommandRPCMediator();
    }

    @Test
    public void testParsingPayloadOne(){
        String payload = "";
        Object response = mediator.runCommand(Command.GETINFO, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadTwo(){
        String payload = "pippo";
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
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);;
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000_label=" + label + "_description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceTwo(){
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000_description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceThree(){
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000_label" + "_description=" + description;
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test
    public void testParsingPayloadInvoiceFour(){
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);;
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000_label=" + label + "_description=" + description + "_expiry=1w";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    @Test(expected = RuntimeException.class)
    public void testParsingPayloadInvoiceFive(){
        String label = "I am vincent and this is my personal test" + (int)(Math.random()*100);;
        String description = "This is a minimal expression for command invoice?";
        String payload = "msatoshi=1000_label=" + label + "_description=" + description + "_expiry=1p";
        Object response = mediator.runCommand(Command.INVOICE, payload);
        TestCase.assertNotNull(response);
    }

    //TODO test invoice on the propriety fallbacks, preimage, exposeprivatechannels
}
