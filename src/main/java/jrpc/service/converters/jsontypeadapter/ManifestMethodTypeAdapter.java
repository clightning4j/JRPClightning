package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.plugins.ICLightningPlugin;
import jrpc.clightning.plugins.rpcmethods.ICLightningRPCMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Features;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Option;

import java.io.IOException;
import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
//TODO after
public class ManifestMethodTypeAdapter extends TypeAdapter<ManifestMethod> {

    private Gson gson;

    public ManifestMethodTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, ManifestMethod value) throws IOException {
        //Stat object getManifest
        out.beginObject();
        //Stat array options
        out.name("options");
        out.beginArray();
        for(Option option : value.getOptions()){
            out.beginObject();
            out.name("name").value(option.getNamePlugin());
            out.name("type").value(option.getType());
            out.name("default").value(option.getDefaultPropriety());
            out.name("description").value(option.getDescriptionPlugin());
            out.endObject();
        }
        out.endArray();
        //end options array
        out.name("rpcmethods");
        //Start array rpc method
        out.beginArray();
        for(ICLightningRPCMethod rpcMethod : value.getRpcMethods()){
            if(!rpcMethod.getName().equals("init") && !rpcMethod.getName().equals("getmanifest")){
                out.beginObject();
                out.name("name").value(rpcMethod.getName());
                out.name("usage").value(rpcMethod.getUsage());
                out.name("description").value(rpcMethod.getDescription());
                if(rpcMethod.getLongDescription() != null && !rpcMethod.getLongDescription().isEmpty()){
                    out.name("long_description").value(rpcMethod.getLongDescription());
                }
                out.endObject();
            }
        }
        out.endArray();
        //end array rpcmethods

        //start array subscriptions
        out.name("subscriptions");
        out.beginArray();
        for(String subscription : value.getSubscriptions()){
            out.value(subscription);
        }
        out.endArray();
        //end array subscriptions

        out.name("hooks");
        //start hooks array
        out.beginArray();

        for(String hook : value.getHooks()){
            out.value(hook);
        }

        out.endArray();
        //end hooks array
        out.name("features");
        gson.getAdapter(Features.class).write(out, value.getFeatures());

        out.endObject();
        //EndObject get manifest
    }

    @Override
    public ManifestMethod read(JsonReader in) throws IOException {
        return null;
    }
}
