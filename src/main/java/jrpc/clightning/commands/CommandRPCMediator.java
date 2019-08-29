package jrpc.clightning.commands;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.service.socket.ISocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CommandRPCMediator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRPCMediator.class);

    private static final String GETINFO = "GETINFO";
    private static final String NEWADDR = "NEWADDR";

    private CLightningSocket socket;
    private Map<String, IRPCCommand> commands = new HashMap<>();

    public CommandRPCMediator() {
        try {
            socket = new CLightningSocket("/media/vincenzo/Maxtor/C-lightning/node/testnet3/lightning-rpc");
        } catch (ServiceException e) {
            throw new RuntimeException("Configuration socket error, Message error is:" + e.getLocalizedMessage());
        }
        commands.put(GETINFO, new CLightningCommandGetInfo());
        commands.put(NEWADDR, new CLightningCommandNewAddress());
    }

    public Object runCommand(Command command, String payload) {
        String runCommand = null;
        if(command.equals(Command.GETINFO)){
            runCommand = GETINFO;
        }else if(command.equals(Command.NEWADDR)){
            runCommand = NEWADDR;
        }else{
            throw new IllegalArgumentException("Command not found");
        }

        IRPCCommand commandSelected = commands.get(runCommand);
        HashMap<String, String> setting = decodePayload(payload);

        try {
            return commandSelected.doRPCCommand(socket, setting);
        } catch (ServiceException e) {
            throw new RuntimeException("Service exception with message error\n" + e.getLocalizedMessage());
        }
    }

    private HashMap<String, String> decodePayload(String payload) {
        if (payload == null || payload.isEmpty()) {
            return new HashMap<>();
        }

        StringTokenizer tokenizer = new StringTokenizer(payload, "");
        LOGGER.debug("Number toke of the payload " + payload + "\nis: " + tokenizer.countTokens());
        HashMap<String, String> configResult = new HashMap<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            LOGGER.debug("Actual token: " + token);
            StringTokenizer tokenProperty = new StringTokenizer(token, "=");
            //TODO token propiety
            while (tokenProperty.hasMoreTokens()) {
                String key = tokenProperty.nextToken();
                LOGGER.debug("Key tokenized: " + key);
                if (tokenProperty.hasMoreTokens()) {
                    String value = tokenProperty.nextToken();
                    LOGGER.debug("Value tokenized: " + value);
                    configResult.put(key, value);
                } else {
                    throw new CLightningException("Error inside the parser payload: the key " + key + " not have a property");
                }
            }
        }

        return configResult;
    }
}
