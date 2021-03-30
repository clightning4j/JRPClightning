package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandStop extends AbstractRPCCommand<String> {

  public CLightningCommandStop() {
    super(Command.STOP.getCommandKey().toLowerCase());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<String>>() {}.getType();
  }
}
