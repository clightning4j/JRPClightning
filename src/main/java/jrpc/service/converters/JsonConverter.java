/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.service.converters;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import jrpc.clightning.model.CLightningFeeRate;
import jrpc.clightning.model.types.bitcoin.BitcoinDestination;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.exceptions.ServiceException;
import jrpc.service.converters.jsontypeadapter.*;
import jrpc.util.ParameterChecker;

/** @author https://github.com/vincenzopalazzo */
public class JsonConverter implements IConverter {

  private static final String ENCODING_DEFAULT = "UTF-8";

  private final String patternFormat = "dd-MM-yyyy HH:mm:ss";

  private final GsonBuilder gsonBuilder;
  private final Gson gson;

  public JsonConverter() {
    this.gsonBuilder = new GsonBuilder();
    gsonBuilder.excludeFieldsWithoutExposeAnnotation();
    gsonBuilder.setDateFormat(patternFormat);
    gsonBuilder.registerTypeAdapter(Object.class, new ObjectTypeAdapter());
    gsonBuilder.registerTypeAdapter(Date.class, new MyDateTypeAdapter());
    gsonBuilder.registerTypeAdapter(
        ManifestMethod.class, new ManifestMethodTypeAdapter(gsonBuilder.create()));
    gsonBuilder.registerTypeAdapter(
        InitMethod.class, new InitMethodTypeAdapter(gsonBuilder.create()));
    gsonBuilder.registerTypeAdapter(
        BitcoinDestination.class, new BitcoinOutputTypeAdapter(gsonBuilder.create()));
    gsonBuilder.registerTypeAdapter(
        CLightningFeeRate.class, new FeeRateTypeAdapter(gsonBuilder.create()));
    gsonBuilder.registerTypeAdapter(
        BitcoinUTXO.class, new BitcoinUTXOTypeAdapter(gsonBuilder.create()));
    this.gson = gsonBuilder.serializeNulls().create();
  }

  @Override
  public String serialization(Object o) {
    return gson.toJson(o);
  }

  @Override
  public Object deserialization(InputStream inputStream, Type type) throws ServiceException {
    ParameterChecker.doCheckObjectNotNull("deserialization", "inputStream", inputStream);
    ParameterChecker.doCheckObjectNotNull("deserialization", "type", type);

    Object response;
    JsonReader reader;
    try {
      reader = new JsonReader(new InputStreamReader(inputStream, ENCODING_DEFAULT));
      response = gson.fromJson(reader, type);
      reader.close();
    } catch (Exception ex) {
      throw new ServiceException(
          "Exception inside the method deserialization to "
              + this.getClass().getSimpleName()
              + "\nMessage: "
              + ex.getLocalizedMessage());
    }
    return response;
  }

  @Override
  public Object deserialization(String jsonForm, Type type) throws ServiceException {
    ParameterChecker.doCheckString("deserialization", "jsonForm", jsonForm, false);
    ParameterChecker.doCheckObjectNotNull("deserialization", "type", type);
    Object response;
    try {
      response = gson.fromJson(jsonForm, type);
    } catch (Exception ex) {
      throw new ServiceException(
          "Exception inside the method deserialization to "
              + this.getClass().getSimpleName()
              + "\nMessage: "
              + ex.getLocalizedMessage());
    }
    return response;
  }

  protected static class MyDateTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
      if (value == null) {
        out.nullValue();
      } else {
        out.value(value.getTime() / 1000);
      }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
      if (in != null) {
        return new Date(in.nextLong() * 1000);
      } else {
        return null;
      }
    }
  }
}
