package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningReserveInputs;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandReserveInputs extends AbstractRPCCommand<CLightningReserveInputs>{

    public CLightningCommandReserveInputs() {
        super(Command.RESERVEINPUTS.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningReserveInputs>>(){}.getType();
    }
}
