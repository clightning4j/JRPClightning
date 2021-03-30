package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningListPays;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandListPays extends AbstractRPCCommand<CLightningListPays> {

  public CLightningCommandListPays() {
    super(Command.LISTPAYS.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningListPays>>() {}.getType();
  }
}
