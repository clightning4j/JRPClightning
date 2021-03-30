package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.types.CLightningPSBT;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandSignPSBT extends AbstractRPCCommand<CLightningPSBT> {

  public CLightningCommandSignPSBT() {
    super(Command.SIGNPSBT.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningCommandSignPSBT>>() {}.getType();
  }
}
