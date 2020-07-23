package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningFeeRate;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandFeeRate extends AbstractRPCCommand<CLightningFeeRate>{

    public CLightningCommandFeeRate() {
        super(Command.FEERATES.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningFeeRate>>(){}.getType();
    }
}
