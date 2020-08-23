/**
 * Copyright 2019-2020 https://github.com/vincenzopalazzo vincenzo.palazzo@protonmail.com
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
package jrpc.clightning.commands;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;

import java.util.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CommandRPCMediator {

    private static final Class TAG = CommandRPCMediator.class;

    private CLightningSocket socket;
    protected Map<Command, IRPCCommand> commands = new EnumMap<>(Command.class);
    protected Map<String, IRPCCommand> cusomCommands = new HashMap<>();

    public CommandRPCMediator(CLightningSocket socket) {
        this.socket = socket;
        initializeMediator();
    }

    protected void initializeMediator() {
        commands.put(Command.GETINFO, new CLightningCommandGetInfo());
        commands.put(Command.NEWADDR, new CLightningCommandNewAddress());
        commands.put(Command.INVOICE, new CLightningCommandInvoice());
        commands.put(Command.LISTINVOICE, new CLightningCommandGetListInvoices());
        commands.put(Command.DELINVOICE, new CLightningCommandDelInvoice());
        commands.put(Command.AUTOCLEANINVOICE, new CLightningCommandAutoCleanInvoice());
        commands.put(Command.TXPREPARE, new CLightningCommandTxPrepare());
        commands.put(Command.TXDISCARD, new CLightningCommandTxDiscard());
        commands.put(Command.TXSEND, new CLightningCommandTxSend());
        commands.put(Command.WITHDRAW, new CLightningCommandWithDraw());
        commands.put(Command.CLOSE, new CLightningCommandClose());
        commands.put(Command.FUNDCHANNEL, new CLightningCommandFundChannel());
        commands.put(Command.LISTFOUNDS, new CLightningCommandListFounds());
        commands.put(Command.CONNECT, new CLightningCommandConnect());
        commands.put(Command.PAY, new CLightningCommandPay());
        commands.put(Command.LISTSENDPAYS, new CLightningCommandListSendPays());
        commands.put(Command.LISTCHANNELS, new CLightningCommandListChannels());
        commands.put(Command.LISTPEERS, new CLightningCommandListPeers());
        commands.put(Command.DECODEPAY, new CLightningCommandDecodePay());
        commands.put(Command.GETROUTE, new CLightningCommandGetRoute());
        commands.put(Command.FEERATES, new CLightningCommandFeeRate());
        commands.put(Command.DISCONNECT, new CLightningCommandDisconnect());
        commands.put(Command.LISTNODES, new CLightningCommandListNodes());
        commands.put(Command.STOP, new CLightningCommandStop());
        commands.put(Command.LISTPAYS, new CLightningCommandListPays());
        commands.put(Command.WAITINVOICE, new CLightningCommandWaitingInvoice());
        commands.put(Command.PING, new CLightningCommandPing());
        commands.put(Command.LISTTRANSACTIONS, new CLightningCommandListTransactions());
        commands.put(Command.HELP, new CLightningCommandHelp());
    }

    @Deprecated
    public Object runCommand(Command command, String payload) {
        if (socket == null) {
            try {
                initializeMediator();
            } catch (CLightningException ex) {
                CLightningLogger.getInstance().error(TAG, "FATAL ERROR: Socket not initialization");
                return null;
            }
        }
        if (!commands.containsKey(command)) {
            throw new CommandException("The command " + command + " not supported yet");
        }
        IRPCCommand commandSelected = commands.get(command);
        HashMap<String, Object> setting = decodePayload(payload);
        try {
            return commandSelected.doRPCCommand(socket, setting);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new CLightningException("Service exception with message error\n" + e.getLocalizedMessage());
        } catch (CommandException e) {
            throw new CLightningException("Error when running the command " + command + ".\n" + e.getLocalizedMessage());
        }
    }

    public Object runCommand(Command command, Map<String, Object> payload) {
        if (socket == null) {
            try {
                initializeMediator();
            } catch (CLightningException ex) {
                CLightningLogger.getInstance().error(TAG, "FATAL ERROR: Socket not initialization");
                return null;
            }
        }
        if (!commands.containsKey(command)) {
            throw new CommandException("The command " + command + " not supported yet");
        }
        IRPCCommand commandSelected = commands.get(command);
        try {
            return commandSelected.doRPCCommand(socket, payload);
        } catch (ServiceException e) {
            e.printStackTrace();
            CLightningLogger.getInstance().error(TAG, e.getLocalizedMessage());
            throw new CLightningException("Service exception with message error\n" + e.getLocalizedMessage());
        } catch (CommandException e) {
            throw new CLightningException("Error when running the command " + command + ".\n" + e.getLocalizedMessage());
        }
    }

    public void registerCommand(ICommandKey key, IRPCCommand command){
        if(key == null || command == null){
            throw new IllegalArgumentException("Key and/or command null");
        }
        cusomCommands.put(key.getCommandKey(), command);
    }

    public <T> T runRegisterCommand(ICommandKey key, HashMap<String, Object> payload){
        if(!cusomCommands.containsKey(key.getCommandKey())){
            throw new IllegalArgumentException("Command with key:" + key.getCommandKey() + " inside the register command cache");
        }
        IRPCCommand command = cusomCommands.get(key.getCommandKey());
        return (T) command.doRPCCommand(socket, payload);
    }

    @Deprecated
    private HashMap<String, Object> decodePayload(String payload) {
        if (payload == null || payload.trim().isEmpty()) {
            return new HashMap<>();
        }
        StringTokenizer tokenizer = new StringTokenizer(payload, "+");
        CLightningLogger.getInstance().debug(TAG,"Number toke of the payload " + payload + "\nis: " + tokenizer.countTokens());
        HashMap<String, Object> configResult = new HashMap<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            CLightningLogger.getInstance().debug(TAG,"Actual token: " + token);
            StringTokenizer tokenProperty = new StringTokenizer(token, "=");
            //TODO token proprieties
            while (tokenProperty.hasMoreTokens()) {
                String key = tokenProperty.nextToken();
                CLightningLogger.getInstance().debug(TAG,"Key tokenized: " + key);
                if (tokenProperty.hasMoreTokens()) {
                    String value = tokenProperty.nextToken();
                    CLightningLogger.getInstance().debug(TAG,"Value tokenized: " + value);
                    List<String> arrayObject = valueToList(value);
                    HashMap<String, String> outputs = valueToHasMap(value);
                    if (arrayObject != null) {
                        configResult.put(key, arrayObject);
                    } else if (outputs != null) {
                        List<String> result = new ArrayList<>();
                        for (Map.Entry<String, String> entry : outputs.entrySet()) {
                            String valueTmp = entry.getKey() + ":" + entry.getValue();
                            result.add(valueTmp);
                        }
                        configResult.put(key, result);
                    } else {
                        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                            Boolean valueBool = Boolean.valueOf(value);
                            configResult.put(key, valueBool);
                        } else {
                            configResult.put(key, value);
                        }
                    }
                } else {
                    throw new CLightningException("Error inside the parser payload: the key " + key + " not have a property");
                }
            }
        }

        return configResult;
    }

    @Deprecated
    protected HashMap<String, String> valueToHasMap(String value) {
        if (value == null) {
            throw new IllegalArgumentException("The value inside the method valueToList inside the class "
                    + this.getClass().getCanonicalName() + " is null");
        }
        if (value.contains("&")) {
            //parsin a list of output
        } else if (value.contains("#")) {
            //Parsing a single output
            StringTokenizer tokenizerOutput = new StringTokenizer(value, "#");
            String key = tokenizerOutput.nextToken();
            CLightningLogger.getInstance().debug(TAG,"The single key is: " + key);
            String valueMap = tokenizerOutput.nextToken();
            CLightningLogger.getInstance().debug(TAG,"The single value is: " + value);
            HashMap<String, String> outputsMap = new HashMap<>();
            outputsMap.put(key, valueMap);
            return outputsMap;
        }
        return null;
    }

    @Deprecated
    protected List<String> valueToList(String value) {
        if (value == null) {
            throw new IllegalArgumentException("The value inside the method valueToList inside the class "
                    + this.getClass().getCanonicalName() + " is null");
        }
        if (value.contains("[") && value.contains("]")) {
            CLightningLogger.getInstance().debug(TAG,"The value is an list and this is value before the substring " + value);
            String containsValue = value.substring(1, value.length() - 1);
            CLightningLogger.getInstance().debug(TAG,"The value substring method parsing is: " + containsValue);

            StringTokenizer parsinList = new StringTokenizer(containsValue, ",");
            List<String> elements = new ArrayList<>();
            while (parsinList.hasMoreTokens()) {
                String element = parsinList.nextToken();
                CLightningLogger.getInstance().debug(TAG,"The element is: " + element);
                elements.add(element);
            }
            return elements;
        }
        CLightningLogger.getInstance().debug(TAG,"The value not is a list");
        return null;

    }
}
