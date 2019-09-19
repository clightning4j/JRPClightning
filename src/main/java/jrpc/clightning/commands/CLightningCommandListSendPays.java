package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningListSendPays;
import jrpc.clightning.model.CLightningPay;
import jrpc.wrapper.response.RPCResponseWrapper;

import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandListSendPays extends AbstractRPCCommand<CLightningListSendPays>{

    private static final String COMMAND_NAME = "listsendpays";

    public CLightningCommandListSendPays() {
        super(COMMAND_NAME);
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningListSendPays>>(){}.getType();
    }
}
