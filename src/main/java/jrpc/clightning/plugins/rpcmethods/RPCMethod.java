package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;

import java.io.*;


/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class RPCMethod implements ICLightningRPCMethod{

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
