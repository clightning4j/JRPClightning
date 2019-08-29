package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningGetInfo;
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
class CLightningCommandGetInfo extends AbstractRPCCommand<CLightningGetInfo>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandGetInfo.class);
    private static final String COMMAND_NAME = "getinfo";

    public CLightningCommandGetInfo() {
        super(COMMAND_NAME);
    }

    @Override
    public CLightningGetInfo doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException {
        super.doRPCCommand(socket, payload);

        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(1546, COMMAND_NAME, payload);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningGetInfo>>(){}.getType();
        RPCResponseWrapper<CLightningGetInfo> response = (RPCResponseWrapper<CLightningGetInfo>) socket.doCall(wrapper, collectionType);
        return response.getResult();
    }
}
