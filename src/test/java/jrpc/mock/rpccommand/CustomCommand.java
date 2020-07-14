package jrpc.mock.rpccommand;

import jrpc.clightning.commands.ICommandKey;

public enum CustomCommand implements ICommandKey {
    DELPAY("DELPAY");

    private String commandKey;

    CustomCommand(String commandKey) {
        this.commandKey = commandKey;
    }

    @Override
    public String getCommandKey() {
        return this.commandKey;
    }
}
