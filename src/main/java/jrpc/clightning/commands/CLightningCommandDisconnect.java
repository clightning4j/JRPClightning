package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandDisconnect extends AbstractRPCCommand<Void> {

  public CLightningCommandDisconnect() {
    super(Command.DISCONNECT.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<RPCResponseWrapper<Void>>>() {}.getType();
  }
}
