package jrpc.clightning.plugins;

import com.google.gson.annotations.Expose;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractPlugin implements ICLightningPlugin {

    @Expose
    private static final Class TAG = AbstractPlugin.class;

    private ManifestMethod manifest;

    public AbstractPlugin() {
        this.manifest = new ManifestMethod();
    }

    public void addRPCMethod(RPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        CLightningLogger.getInstance().debug(TAG,"Added method to list methods of plugin");
        this.manifest.addMethod(method);
    }

    @Override
    public void start() {
        addRPCMethod(this.manifest);
        addRPCMethod(new InitMethod());
    }

    // getter method
    public List<RPCMethod> getRpcMethods() {
        return manifest.getRpcMethods();
    }

    public List<String> getSubscriptions() {
        return manifest.getSubscriptions();
    }

    public List<String> getHooks() {
        return manifest.getHooks();
    }

    public boolean isDynamic() {
        return manifest.getDynamic();
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

}
