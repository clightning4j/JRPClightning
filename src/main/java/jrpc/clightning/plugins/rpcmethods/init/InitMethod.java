package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.annotations.SerializedName;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.service.CLightningConfigurator;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class InitMethod extends AbstractRPCMethod {

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
        //do thing for moment
    }

}
