package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;

public class BitcoinUTXOTypeAdapter extends TypeAdapter<BitcoinUTXO> {

  private final Gson gson;

  public BitcoinUTXOTypeAdapter(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void write(JsonWriter out, BitcoinUTXO value) throws IOException {
    out.beginObject();
    out.name(value.getTxId());
    out.value(value.getIndex());
    out.endObject();
  }

  @Override
  public BitcoinUTXO read(JsonReader in) throws IOException {
    return gson.fromJson(in, BitcoinUTXO.class);
  }
}
