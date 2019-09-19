package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.model.CLightningBitcoinTx;
import jrpc.clightning.model.CLightningPay;
import jrpc.wrapper.response.RPCResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandPay extends AbstractRPCCommand<CLightningPay> {

    private static final String COMMAND_NAME = "pay";

    public CLightningCommandPay() {
        super(COMMAND_NAME);
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningPay>>(){}.getType();
    }
}
