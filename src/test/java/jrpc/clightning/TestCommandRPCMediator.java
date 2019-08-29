package jrpc.clightning;

import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
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
}
