package jrpc.service.converters.jsontypeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.plugins.ICLightningPlugin;

import java.io.IOException;

/**
 * @author https://github.com/vincenzopalazzo
 */
//TODO after
public class PluginsTypeAdapter extends TypeAdapter<ICLightningPlugin> {
    @Override
    public void write(JsonWriter out, ICLightningPlugin value) throws IOException {

    }

    @Override
    public ICLightningPlugin read(JsonReader in) throws IOException {
        return null;
    }
}
