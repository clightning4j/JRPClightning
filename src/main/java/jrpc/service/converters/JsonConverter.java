package jrpc.service.converters;

import com.google.gson.*;
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

    private GsonBuilder gsonBuilder;

    public JsonConverter() {
        this.gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new AdapterDateType());
        gsonBuilder.setPrettyPrinting();
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
    public Object deserialization(InputStream inputStream, Class type) {
        if(inputStream == null || type == null){
            throw new IllegalArgumentException("Arguments are/is null");
        }
        Object response;
        Reader reader;
        try {
            reader = new InputStreamReader(inputStream);
            Gson gson = gsonBuilder.create();
            response = gson.fromJson(reader, type);
        }catch (Exception ex){
            throw new ServiceException("Exception inside the method deserialization to " +
                                        this.getClass().getSimpleName() + "\nMessage: " + ex.getLocalizedMessage());
        }
        return response;
    }

    protected class AdapterDateType implements JsonSerializer<Date>, JsonDeserializer<Date>{

        private String patternFormat = "dd-MM-yyyy HH:mm:ss";

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return null;
        }

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            DateFormat dateFormat = new SimpleDateFormat(patternFormat);
            return new JsonPrimitive(dateFormat.format(src.getTime()));
        }
    }
}
