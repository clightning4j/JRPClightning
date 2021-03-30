package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import jrpc.clightning.model.types.bitcoin.BitcoinDestination;

public class BitcoinOutputTypeAdapter extends TypeAdapter<BitcoinDestination> {

  private Gson gson;

  public BitcoinOutputTypeAdapter(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void write(JsonWriter out, BitcoinDestination value) throws IOException {
    out.beginObject();
    out.name(value.getDestination());
    out.value(value.getAmount());
    out.endObject();
  }

  @Override
  public BitcoinDestination read(JsonReader in) throws IOException {
    return gson.fromJson(in, BitcoinDestination.class);
  }
}
