package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningListChannels;
import jrpc.clightning.model.CLightningListNodes;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandListNodes extends AbstractRPCCommand<CLightningListNodes>{

    public CLightningCommandListNodes() {
        super(Command.LISTNODES.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return  new TypeToken<RPCResponseWrapper<CLightningListNodes>>(){}.getType();
    }
}
