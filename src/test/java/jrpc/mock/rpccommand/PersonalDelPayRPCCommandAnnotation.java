package jrpc.mock.rpccommand;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.annotation.RPCCommand;
import jrpc.clightning.commands.AbstractRPCCommand;
import jrpc.clightning.model.CLightningListPays;
import jrpc.wrapper.response.RPCResponseWrapper;

@RPCCommand(name = "ann_delpay")
public class PersonalDelPayRPCCommandAnnotation extends AbstractRPCCommand<CLightningListPays> {

  private static final String COMMAND_NAME = CustomCommand.ANN_DELPAY.getCommandKey();

  public PersonalDelPayRPCCommandAnnotation() {
    super(COMMAND_NAME);
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningListPays>>() {}.getType();
  }
}
