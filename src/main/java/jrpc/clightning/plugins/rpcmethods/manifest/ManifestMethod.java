package jrpc.clightning.plugins.rpcmethods.manifest;

import com.google.gson.annotations.SerializedName;
import jrpc.clightning.CLightningRPC;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ManifestMethod extends AbstractRPCMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManifestMethod.class);

    private List<Option> options = new ArrayList<>();
    @SerializedName("rpcmethods")
    private List<ICLightningRPCMethod> rpcMethods = new ArrayList<>();
    private List<String> subscriptions = new ArrayList<>();
    private List<String> hooks = new ArrayList<>();
    private Boolean dynamic = Boolean.TRUE;

    public ManifestMethod() {
        super("getmanifest", null, null);
    }

    public ManifestMethod(List<Option> options, List<ICLightningRPCMethod> rpcMethods,
                          List<String> subscriptions, List<String> hooks, Boolean dynamic) {
        this();
        this.options = options;
        this.rpcMethods = rpcMethods;
        this.subscriptions = subscriptions;
        this.hooks = hooks;
        this.dynamic = dynamic;
    }

    @Override
    public void doRun(Object... params) {
        //do nothing for moment
    }

    public boolean addMethods(List<ICLightningRPCMethod> methods){
        if(methods == null || methods.isEmpty()){
            throw new IllegalArgumentException("List of methods empty or null");
        }
        methods.removeIf(method -> (method instanceof ManifestMethod || method instanceof InitMethod));
        return this.rpcMethods.addAll(methods);
    }

    public boolean addOption(String name, String type, String def, String description){
        if((name == null || name.isEmpty())
                || (type == null || type.isEmpty())
                || (def == null || def.isEmpty())
                || (description == null || description.isEmpty())){
            throw new IllegalArgumentException("Argument/s null");
        }

        Option option = new Option(name, type, def, description);
        return this.options.add(option);
    }

    //getter methods
    public List<Option> getOptions() {
        return options;
    }

    public List<ICLightningRPCMethod> getRpcMethods() {
        return rpcMethods;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public List<String> getHooks() {
        return hooks;
    }

    public Boolean getDynamic() {
        return dynamic;
    }
}
