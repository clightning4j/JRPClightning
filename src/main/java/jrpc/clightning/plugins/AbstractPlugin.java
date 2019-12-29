package jrpc.clightning.plugins;

import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractPlugin implements ICLightningPlugin {

    private ManifestMethod manifest = new ManifestMethod();
    private InitMethod intMethod = new InitMethod(Boolean.TRUE);

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

}
