package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.plugins.rpcmethods.init.Configuration;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;

import java.io.IOException;

public class InitMethodTypeAdapter extends TypeAdapter<InitMethod> {

    private Gson gson;

    public InitMethodTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, InitMethod value) throws IOException {
        //start serializzation method init

        //TODO the answer from init method is ignored and I can send only the configuration?
        out.beginObject();

        out.name("configuration");
        gson.getAdapter(Configuration.class).write(out, value.getConfiguration());

        out.endObject();
    }

    @Override
    public InitMethod read(JsonReader in) throws IOException {
        return null;
    }
}
