package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.response.RPCResponseWrapper;
import jrpc.wrapper.socket.RPCUnixRequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandGetListInvoices extends AbstractRPCCommand<CLightningListInvoices>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandGetListInvoices.class);
    private static final String COMMAND_NAME = "listinvoices";

    public CLightningCommandGetListInvoices() {
        super(COMMAND_NAME);
    }

    @Override
    public CLightningListInvoices doRPCCommand(CLightningSocket socket, HashMap<String, Object> payload) throws ServiceException, CommandException {
        super.doRPCCommand(socket, payload);

        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(COMMAND_NAME, payload);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningListInvoices>>(){}.getType();
        RPCResponseWrapper<CLightningListInvoices> response = (RPCResponseWrapper<CLightningListInvoices>) socket.doCall(wrapper, collectionType);
        if(response.getError() != null){
            throw new CommandException("Error inside command with error code: " +
                    response.getError().getCode() + "\nMessage: " + response.getError().getMessage());
        }
        return response.getResult();
    }
}
