package jrpc.clightning.plugins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.service.JRPCLightningLogger;
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
    protected static final Class TAG = AbstractPlugin.class;

    @SerializedName("name")
    protected String namePlugin;
    @SerializedName("rpcmethods")
    protected List<RPCMethod> rpcMethods = new ArrayList<>();
    protected String type;
    @SerializedName("default")
    protected String defaultPropriety;
    @SerializedName("description")
    protected String descriptionPlugin;
    protected List<String> subscriptions = new ArrayList<>();
    protected List<String> hooks = new ArrayList<>();
    protected List<String> features = new ArrayList<>();
    protected boolean dynamic;


    public void addRPCMethod(RPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("Method object null");
        }
        JRPCLightningLogger.getInstance().debug(TAG,"Added method to list methods of plugin");
        this.rpcMethods.add(method);
    }

    @Override
    public void start() {
        ManifestMethod manifestMethod = new ManifestMethod();
        manifestMethod.addMethods(this.rpcMethods);
        addRPCMethod(manifestMethod);
        addRPCMethod(new InitMethod(Boolean.TRUE));
    }

    // getter method
    public String getNamePlugin() {
        return namePlugin;
    }

    public List<RPCMethod> getRpcMethods() {
        return rpcMethods;
    }

    public String getType() {
        return type;
    }

    public String getDefaultPropriety() {
        return defaultPropriety;
    }

    public String getDescriptionPlugin() {
        return descriptionPlugin;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public List<String> getHooks() {
        return hooks;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

}
