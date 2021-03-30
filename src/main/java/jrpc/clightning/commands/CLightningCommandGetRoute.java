package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandGetRoute extends AbstractRPCCommand<CLightningCommandGetRoute> {

  public CLightningCommandGetRoute() {
    super(Command.GETROUTE.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<
        RPCResponseWrapper<RPCResponseWrapper<CLightningCommandGetRoute>>>() {}.getType();
  }
}
