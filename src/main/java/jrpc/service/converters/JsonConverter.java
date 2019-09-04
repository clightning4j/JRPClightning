/**
 * Copyright 2019 https://github.com/vincenzopalazzo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.service.converters;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import jrpc.exceptions.ServiceException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    }

    @Override
    public String serialization(Object o) {
        if(o == null){
            throw new IllegalArgumentException("Argument function is null");
        }
        Gson gson = gsonBuilder.create();
        return gson.toJson(o);
    }

    @Override
    public Object deserialization(InputStream inputStream, Type type) throws ServiceException {
        if(inputStream == null || type == null){
            throw new IllegalArgumentException("Arguments are/is null");
        }
        Object response;
        JsonReader reader;
        try {
            reader = new JsonReader( new InputStreamReader(inputStream, ENCODING_DEFAULT));
            Gson gson = gsonBuilder.create();
            response = gson.fromJson(reader, type);
            //reader.close(); //TODO can I release the connection open with socket?
        }catch (Exception ex){
            throw new ServiceException("Exception inside the method deserialization to " +
                                        this.getClass().getSimpleName() + "\nMessage: " + ex.getLocalizedMessage());
        }
        return response;
    }
}
