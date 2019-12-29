package jrpc.mock;

import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class RPCMockMethod extends AbstractRPCMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCMockMethod.class);

    public RPCMockMethod(String name, String usage, String description) {
        super(name, usage, description);
    }

    public RPCMockMethod(String name, String usage, String description, String longDescription) {
        super(name, usage, description, longDescription);
    }

    @Override
    public void doRun(Object... params) {
        LOGGER.debug("******* MOCK method run *******");
    }
}
