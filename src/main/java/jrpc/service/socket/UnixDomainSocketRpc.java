package jrpc.service.socket;

import jrpc.exceptions.ServiceException;
import jrpc.wrapper.IWrapperSocketCall;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public abstract class UnixDomainSocketRpc implements ISocket{

    protected static final String ENCODING = "UTF-8";


    protected static int id = 1;

    protected AFUNIXSocket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected IConverter converterJson;

    public UnixDomainSocketRpc(String pathSocket) {
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
    public Object doCall(IWrapperSocketCall wrapperSocket, Class typeResult) {
        if(wrapperSocket == null){
            throw new IllegalArgumentException("The argument is null");
        }

        String serializationForm = converterJson.serialization(wrapperSocket);

        try {
            this.outputStream.write(serializationForm.getBytes(ENCODING));
            this.outputStream.flush();
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                                        + " with message\n" + e.getLocalizedMessage());
        }

        //Object result = null;
       /* try {
            //TODO il method serializza non funziona con gli input al di fuori dei file giusto?
           // String respose = IOUtil.readFullyAsString(inputStream, ENCODING);
            result = converterJson.deserialization(inputStream, typeResult);
            return result;
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                    + " with message\n" + e.getLocalizedMessage());
        }*/

        return converterJson.deserialization(inputStream, typeResult);
    }
}
