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
            reader.close();
        }catch (Exception ex){
            throw new ServiceException("Exception inside the method deserialization to " +
                                        this.getClass().getSimpleName() + "\nMessage: " + ex.getLocalizedMessage());
        }
        return response;
    }
}
