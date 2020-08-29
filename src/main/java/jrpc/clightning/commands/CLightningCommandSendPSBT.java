package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.types.CLightningTransaction;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandSendPSBT extends AbstractRPCCommand<CLightningTransaction>{

    public CLightningCommandSendPSBT() {
        super(Command.SENDPSBT.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningTransaction>>(){}.getType();
    }
}
