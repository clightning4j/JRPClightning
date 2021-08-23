package jrpc.mock.rpccommand;

import java.util.Locale;
import jrpc.clightning.commands.ICommandKey;

public enum CustomCommand implements ICommandKey {
  DELPAY("DELPAY"),
  ANN_DELPAY("ANN_DELPAY");
  ;

  private String commandKey;

  CustomCommand(String commandKey) {
    this.commandKey = commandKey.toLowerCase(Locale.ROOT);
  }

  @Override
  public String getCommandKey() {
    return this.commandKey;
  }
}
