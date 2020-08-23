package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningHelp;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandHelp extends AbstractRPCCommand<CLightningHelp>{

    public CLightningCommandHelp() {
        super(Command.HELP.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningHelp>>(){}.getType();
    }
}
