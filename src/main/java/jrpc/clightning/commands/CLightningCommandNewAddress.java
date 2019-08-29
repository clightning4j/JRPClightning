package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.model.CLightningNewAddress;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.RPCResponseWrapper;
import jrpc.wrapper.RPCUnixRequestMethod;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandNewAddress extends AbstractRPCCommand<CLightningNewAddress>{

    public CLightningCommandNewAddress() {
        super("newaddr");
    }

    @Override
    public CLightningNewAddress doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException {
        super.doRPCCommand(socket, payload);


        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(1546, commandName, payload);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningNewAddress>>(){}.getType();
        RPCResponseWrapper<CLightningNewAddress> response = (RPCResponseWrapper<CLightningNewAddress>) socket.doCall(wrapper, collectionType);
        return response.getResult();
    }
}
