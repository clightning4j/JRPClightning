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
package jrpc.clightning.commands;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.service.socket.ISocket;
import jrpc.wrapper.response.ErrorResponse;
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
    private static final String INVOICE = "INVOICE";

    private CLightningSocket socket;
    private Map<String, IRPCCommand> commands = new HashMap<>();

    public CommandRPCMediator() {
        try {
            socket = new CLightningSocket("/media/vincenzo/Maxtor/C-lightning/node/testnet/lightning-rpc");
        } catch (ServiceException e) {
            throw new RuntimeException("Configuration socket error, Message error is:" + e.getLocalizedMessage());
        }
        commands.put(GETINFO, new CLightningCommandGetInfo());
        commands.put(NEWADDR, new CLightningCommandNewAddress());
        commands.put(INVOICE, new CLightningCommandInvoice());
    }

    public Object runCommand(Command command, String payload) {
        String runCommand = null;
        if(command.equals(Command.GETINFO)){
            runCommand = GETINFO;
        }else if(command.equals(Command.NEWADDR)){
            runCommand = NEWADDR;
        }else if(command.equals(Command.INVOICE)){
            runCommand = INVOICE;
        }else{
            throw new IllegalArgumentException("Command not found");
        }

        IRPCCommand commandSelected = commands.get(runCommand);
        HashMap<String, String> setting = decodePayload(payload);

        try {
            return commandSelected.doRPCCommand(socket, setting);
        } catch (ServiceException e) {
            throw new RuntimeException("Service exception with message error\n" + e.getLocalizedMessage());
        } catch (CommandException e) {
            throw new RuntimeException("Error whent running the command " + runCommand + ".\n" + e.getLocalizedMessage());
        }
    }

    private HashMap<String, String> decodePayload(String payload) {
        if (payload == null || payload.isEmpty()) {
            return new HashMap<>();
        }

        StringTokenizer tokenizer = new StringTokenizer(payload, "_");
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
