/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.service.converters;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jrpc.clightning.model.CLightningFeeRate;
import jrpc.clightning.model.types.BitcoinOutput;
import jrpc.clightning.plugins.rpcmethods.init.InitMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.ManifestMethod;
import jrpc.exceptions.ServiceException;
import jrpc.service.converters.jsontypeadapter.BitcoinOutputTypeAdapter;
import jrpc.service.converters.jsontypeadapter.FeeRateTypeAdapter;
import jrpc.service.converters.jsontypeadapter.InitMethodTypeAdapter;
import jrpc.service.converters.jsontypeadapter.ManifestMethodTypeAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class JsonConverter implements IConverter {

    private static final String ENCODING_DEFAULT = "UTF-8";

    private String patternFormat = "dd-MM-yyyy HH:mm:ss";

    private GsonBuilder gsonBuilder;

    public JsonConverter() {
        this.gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setDateFormat(patternFormat);
        gsonBuilder.registerTypeAdapter(Date.class, new MyDateTypeAdapter());
        gsonBuilder.registerTypeAdapter(ManifestMethod.class, new ManifestMethodTypeAdapter(gsonBuilder.create()));
        gsonBuilder.registerTypeAdapter(InitMethod.class, new InitMethodTypeAdapter(gsonBuilder.create()));
        gsonBuilder.registerTypeAdapter(BitcoinOutput.class, new BitcoinOutputTypeAdapter(gsonBuilder.create()));
        gsonBuilder.registerTypeAdapter(CLightningFeeRate.class, new FeeRateTypeAdapter(gsonBuilder.create()));
    }

    @Override
    public String serialization(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Argument function is null");
        }
        Gson gson = gsonBuilder.create();
        return gson.toJson(o);
    }

    @Override
    public Object deserialization(InputStream inputStream, Type type) throws ServiceException {
        if (inputStream == null || type == null) {
            throw new IllegalArgumentException("Arguments are/is null");
        }
        Object response;
        JsonReader reader;
        try {
            reader = new JsonReader(new InputStreamReader(inputStream, ENCODING_DEFAULT));
            Gson gson = gsonBuilder.create();
            response = gson.fromJson(reader, type);
            //reader.close(); //TODO can I release the connection open with socket?
        } catch (Exception ex) {
            throw new ServiceException("Exception inside the method deserialization to " +
                    this.getClass().getSimpleName() + "\nMessage: " + ex.getLocalizedMessage());
        }
        return response;
    }

    @Override
    public Object deserialization(String jsonForm, Type type) throws ServiceException {
        if ((jsonForm == null || jsonForm.isEmpty() ) || type == null) {
            throw new IllegalArgumentException("Arguments are/is null");
        }
        Object response;
        try {
            Gson gson = gsonBuilder.create();
            response = gson.fromJson(jsonForm, type);
        } catch (Exception ex) {
            throw new ServiceException("Exception inside the method deserialization to " +
                    this.getClass().getSimpleName() + "\nMessage: " + ex.getLocalizedMessage());
        }
        return response;
    }


    protected class MyDateTypeAdapter extends TypeAdapter<Date> {
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
