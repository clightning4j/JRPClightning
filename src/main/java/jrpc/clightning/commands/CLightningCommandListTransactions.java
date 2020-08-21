package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningListTransactions;
import jrpc.clightning.model.types.CLightningPing;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandListTransactions extends AbstractRPCCommand<CLightningListTransactions> {

    public CLightningCommandListTransactions() {
        super(Command.LISTTRANSACTIONS.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningListTransactions>>(){}.getType();
    }
}
