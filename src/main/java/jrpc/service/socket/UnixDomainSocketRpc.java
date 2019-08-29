package jrpc.service.socket;

import jrpc.exceptions.ServiceException;
import jrpc.wrapper.socket.IWrapperSocketCall;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class UnixDomainSocketRpc implements ISocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnixDomainSocketRpc.class);
    protected static final String ENCODING = "UTF-8";

    protected AFUNIXSocket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected IConverter converterJson;

    public UnixDomainSocketRpc(String pathSocket) throws ServiceException {
        File file = new File(pathSocket);

        try {
            this.socket = AFUNIXSocket.newInstance();
            this.socket.connect(new AFUNIXSocketAddress(file));
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.converterJson = new JsonConverter();
        } catch (IOException e) {
            throw new ServiceException("Exception inside the method deserialization to " +
                    this.getClass().getSimpleName() + " with message\n" + e.getLocalizedMessage());
        }
    }

    @Override
    public Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult) throws ServiceException {
        if (wrapperSocket == null) {
            throw new IllegalArgumentException("The argument is null");
        }
        String serializationForm = converterJson.serialization(wrapperSocket);
        LOGGER.debug("Request: \n" + serializationForm);
        try {
            this.outputStream.write(serializationForm.getBytes(ENCODING));
            this.outputStream.flush();
            LOGGER.debug("Run request");
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                    + " with message\n" + e.getLocalizedMessage());
        }
        Object o = converterJson.deserialization(inputStream, typeResult);
        LOGGER.debug("Response\n" + converterJson.serialization(o));
        return o;
    }
}
