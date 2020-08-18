package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningBitcoinTx;
import jrpc.clightning.model.CLightningListPays;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandListPays extends AbstractRPCCommand<CLightningListPays>{

    public CLightningCommandListPays() {
        super(Command.LISTPAYS.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningListPays>>(){}.getType();
    }
}
