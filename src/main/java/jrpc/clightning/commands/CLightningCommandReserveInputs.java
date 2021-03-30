package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningReserveInputs;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandReserveInputs extends AbstractRPCCommand<CLightningReserveInputs> {

  public CLightningCommandReserveInputs() {
    super(Command.RESERVEINPUTS.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningReserveInputs>>() {}.getType();
  }
}
