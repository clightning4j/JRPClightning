package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.annotations.SerializedName;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class RPCMethod implements ICLightningRPCMethod{

    protected String name;
    protected String usage;
    protected String description;
    @SerializedName("long_description")
    protected String longDescription;

    public RPCMethod(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public RPCMethod(String name, String usage, String description, String longDescription) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.longDescription = longDescription;
    }

    @Override
    public String toString() {
        IConverter converter = new JsonConverter();
        return converter.serialization(this);
    }

    @Override
    public void doRun(Object... params) {
        //Do nothing for moment
    }

    //Getter
    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return longDescription;
    }
}