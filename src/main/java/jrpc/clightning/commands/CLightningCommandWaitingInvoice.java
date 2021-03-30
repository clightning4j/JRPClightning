package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandWaitingInvoice extends AbstractRPCCommand<CLightningInvoice> {

  public CLightningCommandWaitingInvoice() {
    super(Command.WAITINVOICE.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningInvoice>>() {}.getType();
  }
}
