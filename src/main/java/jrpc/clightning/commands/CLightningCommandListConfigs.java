package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.annotation.RPCCommand;
import jrpc.clightning.model.CLightningListConfigs;
import jrpc.wrapper.response.RPCResponseWrapper;

@RPCCommand(custom = false, commandName = Command.LISTCONFIGS)
public class CLightningCommandListConfigs extends AbstractRPCCommand<CLightningListConfigs> {

  public CLightningCommandListConfigs() {
    super(Command.LISTCONFIGS.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningListConfigs>>() {}.getType();
  }
}
