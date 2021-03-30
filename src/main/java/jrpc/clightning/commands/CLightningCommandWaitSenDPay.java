package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.types.CLightningSendPay;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandWaitSenDPay extends AbstractRPCCommand<CLightningSendPay> {

  public CLightningCommandWaitSenDPay() {
    super(Command.WAITSENDPAY.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningSendPay>>() {}.getType();
  }
}
