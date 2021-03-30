package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningListTransactions;
import jrpc.wrapper.response.RPCResponseWrapper;

public class CLightningCommandListTransactions
    extends AbstractRPCCommand<CLightningListTransactions> {

  public CLightningCommandListTransactions() {
    super(Command.LISTTRANSACTIONS.getCommandKey());
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningListTransactions>>() {}.getType();
  }
}
