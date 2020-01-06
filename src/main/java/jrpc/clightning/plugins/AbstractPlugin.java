package jrpc.clightning.plugins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractPlugin implements ICLightningPlugin {

    @Expose
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractPlugin.class);

    @SerializedName("rpcmethods")
    protected List<RPCMethod> rpcMethods = new ArrayList<>();

    public void addRPCMethod(RPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        LOGGER.debug("Added method to list methods of plugin");
        this.rpcMethods.add(method);
    }

    @Override
    public void start() {
        ManifestMethod manifestMethod = new ManifestMethod();
        manifestMethod.addMethods(this.rpcMethods);
        addRPCMethod(manifestMethod);
        addRPCMethod(new InitMethod(Boolean.TRUE));
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

}
