package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandStop extends AbstractRPCCommand<String>{

    public CLightningCommandStop() {
        super(Command.STOP.getCommandKey().toLowerCase());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<String>>(){}.getType();
    }
}
