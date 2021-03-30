package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.types.CLightningTransaction;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandSendPSBT extends AbstractRPCCommand<CLightningTransaction> {

  public CLightningCommandSendPSBT() {
    super(Command.SENDPSBT.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningTransaction>>() {}.getType();
  }
}
