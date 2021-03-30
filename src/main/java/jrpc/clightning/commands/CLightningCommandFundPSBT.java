package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningFundPSBT;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandFundPSBT extends AbstractRPCCommand<CLightningFundPSBT> {

  public CLightningCommandFundPSBT() {
    super(Command.FUNDPSBT.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningFundPSBT>>() {}.getType();
  }
}
