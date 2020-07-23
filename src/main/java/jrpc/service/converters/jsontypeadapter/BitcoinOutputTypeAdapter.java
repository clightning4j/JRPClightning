package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.model.types.BitcoinOutput;

import java.io.IOException;

public class BitcoinOutputTypeAdapter extends TypeAdapter<BitcoinOutput> {

    private Gson gson;

    public BitcoinOutputTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, BitcoinOutput value) throws IOException {
        out.beginObject();
        out.name(value.getAddress());
        out.value(value.getAmount());
        out.endObject();
    }

    @Override
    public BitcoinOutput read(JsonReader in) throws IOException {
        return gson.fromJson(in, BitcoinOutput.class);
    }
}
