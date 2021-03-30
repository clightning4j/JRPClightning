package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.types.CLightningPing;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandPing extends AbstractRPCCommand<CLightningPing> {

  public CLightningCommandPing() {
    super(Command.PING.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningPing>>() {}.getType();
  }
}
