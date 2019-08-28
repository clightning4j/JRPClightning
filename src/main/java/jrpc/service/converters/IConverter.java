package jrpc.service.converters;

import java.io.InputStream;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IConverter {

    String serialization(Object o);

    Object deserialization(InputStream inputStream, Class type);
}
