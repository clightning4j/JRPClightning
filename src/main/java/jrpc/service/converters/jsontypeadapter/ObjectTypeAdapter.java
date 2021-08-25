package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectTypeAdapter extends TypeAdapter<Object> {

  private final TypeAdapter<Object> delegate = new Gson().getAdapter(Object.class);

  @Override
  public void write(JsonWriter out, Object value) throws IOException {
    delegate.write(out, value);
  }

  @Override
  public Object read(JsonReader in) throws IOException {
    JsonToken token = in.peek();
    switch (token) {
      case BEGIN_ARRAY:
        List<Object> list = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
          list.add(read(in));
        }
        in.endArray();
        return list;

      case BEGIN_OBJECT:
        Map<String, Object> map = new LinkedTreeMap<>();
        in.beginObject();
        while (in.hasNext()) {
          map.put(in.nextName(), read(in));
        }
        in.endObject();
        return map;

      case STRING:
        return in.nextString();

      case NUMBER:
        // return in.nextDouble();
        String n = in.nextString();
        if (n.indexOf('.') != -1) {
          return Double.parseDouble(n);
        }
        return Long.parseLong(n);

      case BOOLEAN:
        return in.nextBoolean();

      case NULL:
        in.nextNull();
        return null;

      default:
        throw new IllegalStateException();
    }
  }
}
