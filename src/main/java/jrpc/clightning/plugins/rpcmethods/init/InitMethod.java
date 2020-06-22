package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.JsonObject;
import jrpc.clightning.CLightningConstant;
import jrpc.clightning.model.CLightningModelMediator;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.JsonConverter;
import netscape.javascript.JSObject;

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
    //TODO this method collect data to configure the plugin with clightning implementation.
    //TODO this information will be ignored from clightning node
    public String doRun(Object... params) {
        CLightningLogger.getInstance().debug(InitMethod.class, "**** Called rpc method init ****");
        //TODO read configuration
        return "{}";
    }

    //getter
    public Configuration getConfiguration() {
        return configuration;
    }
}
