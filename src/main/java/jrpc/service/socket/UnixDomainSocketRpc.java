/**
 * UnixDomainSocketRpc the base class to handle communication between
 * JLightning and the c-lightning node over the UnixDomainSocket
 *
 * This file is basically a port of the pylightning python client library
 * that comes with c-lightning.
 *
 * The Author of this Java Client library is Rene Pickhardt and Vincenzo Palazzo
 * He also holds the copyright of this file. The library is licensed with
 * a BSD-style license. Have a look at the LICENSE file.
 *
 * @author Rene Pickhardt
 * If you like this library consider a donation via bitcoin or the lightning
 *  network at http://ln.rene-pickhardt.de
 *
 * @author https://github.com/vincenzopalazzo
 * vincenzopalazzo apported the modific from this pull request https://github.com/ElementsProject/lightning/pull/2223
 */
package jrpc.service.socket;

import jrpc.clightning.exceptions.CLightningException;
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
import java.net.InetSocketAddress;

public abstract class UnixDomainSocketRpc implements ISocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnixDomainSocketRpc.class);
    protected static final String ENCODING = "UTF-8";

    protected AFUNIXSocket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected IConverter converterJson;
    protected String pathSocket;

    public UnixDomainSocketRpc(String pathSocket) throws ServiceException {
        if(pathSocket == null || pathSocket.isEmpty()){
            if(pathSocket != null){
                throw new ServiceException("Path socket is null");
            }else{
                throw new ServiceException("Path socket is empty");
            }
        }
        File file = new File(pathSocket);
        this.pathSocket = pathSocket;
        try {
            this.socket = AFUNIXSocket.newInstance();
            this.socket.connect(new AFUNIXSocketAddress(file));
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.converterJson = new JsonConverter();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException("Exception inside the method deserialization to " +
                    this.getClass().getSimpleName() + " with message\n" + e.getLocalizedMessage());
        }
    }

    @Override
    public Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult) throws ServiceException {
        if (wrapperSocket == null) {
            throw new IllegalArgumentException("The argument wrapperSocket is null");
        }
        if(socket.isClosed()){
            try {
                LOGGER.debug("UnixDomainSocketRpc: path is " + pathSocket);
                File fileRPC = new File(pathSocket);
                if(fileRPC.exists()){
                    InetSocketAddress socketAddress = new AFUNIXSocketAddress(fileRPC);
                    LOGGER.error("BEFORE CONNECT INSIDE METHOD doCall");
                    this.socket = AFUNIXSocket.newInstance();
                    this.socket.connect(socketAddress);
                    this.inputStream = socket.getInputStream();
                    this.outputStream = socket.getOutputStream();
                    LOGGER.error("AFTER CONNECT INSIDE METHOD doCall");
                }else {
                    LOGGER.error("File not exist inside the path: " + pathSocket);
                    throw new CLightningException("File not exist inside the path: " + pathSocket);
                }
            } catch (IOException e) {
                throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                        + " with message\n" + e.getLocalizedMessage());
            }
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
        try {
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                    + " with message\n" + e.getLocalizedMessage());
        }
        LOGGER.debug("Response\n" + converterJson.serialization(o));
        return o;
    }
}
