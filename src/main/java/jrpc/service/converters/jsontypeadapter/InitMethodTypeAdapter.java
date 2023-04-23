package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;

import static java.util.Objects.requireNonNull;

public class InitMethodTypeAdapter extends TypeAdapter<InitMethod> {

  private final Gson gson;

  public InitMethodTypeAdapter(Gson gson) {
      this.gson = requireNonNull(gson);
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
