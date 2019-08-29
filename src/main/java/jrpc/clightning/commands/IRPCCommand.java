package jrpc.clightning.commands;

import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.RPCUnixRequestMethod;

import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
interface IRPCCommand<T> {

    T doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException;
}
