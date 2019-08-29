package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningNewAddress;
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
public class CLightningCommandNewAddress extends AbstractRPCCommand<CLightningNewAddress>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandNewAddress.class);
    private static final String IDENTIFIER_KEY = "addresstyp";
    private static final String COMMAND_NAME = "newaddr";

    public CLightningCommandNewAddress() {
        super(COMMAND_NAME);
    }

    @Override
    public CLightningNewAddress doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException {
        super.doRPCCommand(socket, payload);

        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(1546, commandName, payload);
        String addrType = payload.get(IDENTIFIER_KEY);
        LOGGER.debug("addr type: " + addrType);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningNewAddress>>(){}.getType();
        RPCResponseWrapper<CLightningNewAddress> response = (RPCResponseWrapper<CLightningNewAddress>) socket.doCall(wrapper, collectionType);
        return response.getResult();
    }
}
