package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningListNodes;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandListNodes extends AbstractRPCCommand<CLightningListNodes> {

  public CLightningCommandListNodes() {
    super(Command.LISTNODES.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningListNodes>>() {}.getType();
  }
}
