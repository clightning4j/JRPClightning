package jrpc.clightning.plugins.rpcmethods.init;

import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.service.CLightningConfigurator;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class InitMethod extends RPCMethod {

    private Configuration configuration;

    public InitMethod(Boolean startup) {
        super("init", null, null);
        //TODO assicure that this method is every different to null
        String socketPaht = CLightningConfigurator.getInstance().getSocketPath();
        String socketName = CLightningConfigurator.getInstance().getSocketFileName();
        configuration = new Configuration(socketPaht, socketName, startup);
    }

    @Override
    public void doRun(Object... params) {
        //TODO implement this code
    }

    //getter
    public Configuration getConfiguration() {
        return configuration;
    }
}
