package jrpc.clightning.plugins.rpcmethods.manifest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import jrpc.clightning.CLightningConstant;
import jrpc.clightning.model.CLightningProprietiesMediator;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Features;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.JsonConverter;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ManifestMethod extends RPCMethod {

    private static final Class TAG = ManifestMethod.class;

    private List<Option> options = new ArrayList<>();
    @SerializedName("rpcmethods")
    private List<RPCMethod> rpcMethods = new ArrayList<>();
    private List<String> subscriptions = new ArrayList<>();
    private List<String> hooks = new ArrayList<>();
    private Features features = new Features();
    private boolean dynamic = true;

    public ManifestMethod() {
        super("getmanifest", null, null);
    }

    @Override
    public String doRun(Object... params) {
        boolean containsOptionValue = CLightningProprietiesMediator.getInstance().containsValue(CLightningConstant.OPTIONS);
        if(containsOptionValue){
            Option option = (Option) CLightningProprietiesMediator.getInstance().getValue(CLightningConstant.OPTIONS);
            this.addOption(option);
        }
        JsonConverter converter = new JsonConverter();
        String result = converter.serialization(this);
        CLightningLogger.getInstance().debug(TAG, "**** result method getmanifest: \n" + result);
        return result;
    }

    @Override
    public void doRun(CLightningJsonObject request, CLightningJsonObject response) {
        // TODO refactoring this information; how I can store the options?
        boolean containsOptionValue = CLightningProprietiesMediator.getInstance().containsValue(CLightningConstant.OPTIONS);
        if(containsOptionValue){
            Option option = (Option) CLightningProprietiesMediator.getInstance().getValue(CLightningConstant.OPTIONS);
            this.addOption(option);
        }
        CLightningLogger.getInstance().debug(TAG, "**** result method getmanifest: \n" + response);
        JsonConverter converter = new JsonConverter();
        JsonObject getManifet = (JsonObject) converter.deserialization(converter.serialization(this), JsonObject.class);
        response.mapping(getManifet);
    }

    public void addFeature(String node, String channel, String init, String invoice){
        //TODO if empty
        this.features = new Features(node, channel, init, invoice);
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

    @Deprecated
    public void addOption(ICLightningPlugin plugin){
        if(plugin == null){
            throw new IllegalArgumentException("Argument is null");
        }
        //Option option = new Option(plugin);
        //this.options.add(option);
    }

    public void addOption(Option option){
        if(option == null){
            CLightningLogger.getInstance().error(TAG, "Option null in method addOption");
            throw new IllegalArgumentException("option null");
        }
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
