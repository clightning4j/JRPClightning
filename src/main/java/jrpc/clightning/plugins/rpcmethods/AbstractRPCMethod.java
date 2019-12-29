package jrpc.clightning.plugins.rpcmethods;

import com.google.gson.annotations.SerializedName;
import jdk.internal.org.objectweb.asm.Type;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class AbstractRPCMethod implements ICLightningRPCMethod{

    protected String name;
    protected String usage;
    protected String description;
    @SerializedName("long_description")
    protected String longDescription;

    public AbstractRPCMethod(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public AbstractRPCMethod(String name, String usage, String description, String longDescription) {
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
