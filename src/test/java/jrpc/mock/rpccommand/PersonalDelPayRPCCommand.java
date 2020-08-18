package jrpc.mock.rpccommand;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.commands.AbstractRPCCommand;
import jrpc.clightning.model.types.CLightningListPay;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class PersonalDelPayRPCCommand extends AbstractRPCCommand<CLightningListPay> {

    private static final String COMMAND_NAME = "delpay";

    public PersonalDelPayRPCCommand() {
        super(COMMAND_NAME);
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<RPCResponseWrapper<CLightningListPay>>>(){}.getType();
    }
}
