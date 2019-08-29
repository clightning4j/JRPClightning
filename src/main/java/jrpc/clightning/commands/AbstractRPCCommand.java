package jrpc.clightning.commands;

import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;

import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractRPCCommand<T> implements IRPCCommand<T>{

    protected String commandName;

    public AbstractRPCCommand(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public T doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException {
        if(socket == null || payload == null){
            throw new IllegalArgumentException("Methods is/are null");
        }
        return null;
    }
}
