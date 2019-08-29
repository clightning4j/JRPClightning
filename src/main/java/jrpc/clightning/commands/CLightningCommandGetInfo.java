package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.RPCResponseWrapper;
import jrpc.wrapper.RPCUnixRequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.TypeElement;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
class CLightningCommandGetInfo implements IRPCCommand<CLightningGetInfo>{

    static final String COMMAND_NAME = "getinfo";
    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandGetInfo.class);

    @Override
    public CLightningGetInfo doRPCCommand(CLightningSocket socket, HashMap<String, String> payload) throws ServiceException {
        if(socket == null || payload == null){
            throw new IllegalArgumentException("Methods is/are null");
        }

        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(1546, COMMAND_NAME, payload);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningGetInfo>>(){}.getType();
        RPCResponseWrapper<CLightningGetInfo> response = (RPCResponseWrapper<CLightningGetInfo>) socket.doCall(wrapper, collectionType);
        return response.getResult();
    }
}
