package jrpc.clightning.plugins.rpcmethods.manifest;

import com.google.gson.annotations.SerializedName;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Features;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ManifestMethod extends RPCMethod {

    private List<Option> options = new ArrayList<>();
    @SerializedName("rpcmethods")
    private List<RPCMethod> rpcMethods = new ArrayList<>();
    private List<String> subscriptions = new ArrayList<>();
    private List<String> hooks = new ArrayList<>();
    private Features features;
    private Boolean dynamic = Boolean.TRUE;

    public ManifestMethod() {
        super("getmanifest", null, null);
    }

    public ManifestMethod(List<Option> options, List<RPCMethod> rpcMethods,
                          List<String> subscriptions, List<String> hooks, Features features, Boolean dynamic) {
        this();
        this.options = options;
        this.rpcMethods = rpcMethods;
        this.subscriptions = subscriptions;
        this.hooks = hooks;
        this.features = features;
        this.dynamic = dynamic;
    }

    public ManifestMethod(ICLightningPlugin plugin) {
        this();
        this.options = new ArrayList<>();
        options.add(new Option(plugin));
        this.rpcMethods = plugin.getRpcMethods();
        this.subscriptions = plugin.getSubscriptions();
        this.hooks = plugin.getHooks();
        this.dynamic = plugin.isDynamic();
    }

    @Override
    public void doRun(Object... params) {
        //do nothing for moment
    }

    public void addFeature(String node, String init, String invoice){
        //TODO if empty
        this.features = new Features(node, init, invoice);
    }

    public void addMethods(List<RPCMethod> methods){
        if(methods == null){
            throw new IllegalArgumentException("List of methods empty or null");
        }
        methods.removeIf(method -> (method instanceof ManifestMethod || method instanceof InitMethod));
        this.rpcMethods.addAll(methods);
    }

    public void addMethod(RPCMethod method){
        if(method == null){
            throw new IllegalArgumentException("List of methods empty or null");
        }
        this.rpcMethods.add(method);
    }

    public void addOption(ICLightningPlugin plugin){
        if(plugin == null){
            throw new IllegalArgumentException("Argument is null");
        }
        Option option = new Option(plugin);
        this.options.add(option);
    }

    public void addSubscriptions(String subscription){
        if(subscription == null){
            throw new IllegalArgumentException("Subscription null");
        }
        this.subscriptions.add(subscription);
    }

    public void addHook(String hook){
        if(hook == null){
            throw new IllegalArgumentException("Hook null");
        }
        this.hooks.add(hook);
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    //getter methods
    public List<Option> getOptions() {
        return options;
    }

    public List<RPCMethod> getRpcMethods() {
        return rpcMethods;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public List<String> getHooks() {
        return hooks;
    }

    public Features getFeatures() {
        return features;
    }

    public Boolean getDynamic() {
        return dynamic;
    }
}
