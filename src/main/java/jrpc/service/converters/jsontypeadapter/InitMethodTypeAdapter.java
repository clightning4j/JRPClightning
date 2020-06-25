package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;

import java.io.IOException;

public class InitMethodTypeAdapter extends TypeAdapter<InitMethod> {

    private Gson gson;

    public InitMethodTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, InitMethod value) throws IOException {
        out.beginObject();
        out.endObject();
    }

    @Override
    public InitMethod read(JsonReader in) throws IOException {
        return null;
    }
}
