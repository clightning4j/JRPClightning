package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningListPays;
import jrpc.clightning.model.types.CLightningSendPay;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

public class CLightningCommandWaitSenDPay extends AbstractRPCCommand<CLightningSendPay> {

    public CLightningCommandWaitSenDPay() {
        super(Command.WAITSENDPAY.getCommandKey());
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningSendPay>>(){}.getType();
    }
}
