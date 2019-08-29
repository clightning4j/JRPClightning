package jrpc.service.converters;

import jrpc.exceptions.ServiceException;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IConverter {

    String serialization(Object o);

    Object deserialization(InputStream inputStream, Type type) throws ServiceException;
}
