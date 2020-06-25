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
import jrpc.service.CLightningLogger;
import jrpc.wrapper.socket.IWrapperSocketCall;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.SocketException;

public abstract class UnixDomainSocketRpc implements ISocket {

    private static final Class TAG = UnixDomainSocketRpc.class;
    protected static final String ENCODING = "UTF-8";

    protected AFUNIXSocket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected IConverter converterJson;
    protected String pathSocket;

    public UnixDomainSocketRpc(String pathSocket){
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
            CLightningLogger.getInstance().error(TAG, e.getMessage());
            throw new ServiceException("Exception inside the method deserialization to " +
                    this.getClass().getSimpleName() + " with message\n" + e.getLocalizedMessage());
        }
    }

    @Override
    public int getReceiveBufferSize() throws SocketException {
        return socket.getReceiveBufferSize();
    }

    @Override
    public void close() throws ServiceException {
         try {
             if(socket.isClosed()){
                 socket.shutdownInput();
                 socket.shutdownOutput();
                 socket.close();
             }
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                    + " with message\n" + e.getLocalizedMessage());
        }
    }

    @Override
    public boolean isOpen() {
        return socket != null && !socket.isClosed();
    }

    @Override
    public Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult) throws ServiceException {
        if (wrapperSocket == null) {
            throw new IllegalArgumentException("The argument wrapperSocket is null");
        }
        if(socket.isClosed()){
            try {
                CLightningLogger.getInstance().debug(TAG, "UnixDomainSocketRpc: path is " + pathSocket);
                File fileRPC = new File(pathSocket);
                if(fileRPC.exists()){
                    InetSocketAddress socketAddress = new AFUNIXSocketAddress(fileRPC);
                    CLightningLogger.getInstance().error(TAG,"BEFORE CONNECT INSIDE METHOD doCall");
                    this.socket = AFUNIXSocket.newInstance();
                    this.socket.connect(socketAddress);
                    this.inputStream = socket.getInputStream();
                    this.outputStream = socket.getOutputStream();
                    CLightningLogger.getInstance().error(TAG,"AFTER CONNECT INSIDE METHOD doCall");
                }else {
                    CLightningLogger.getInstance().error(TAG,"File not exist inside the path: " + pathSocket);
                    throw new CLightningException("File not exist inside the path: " + pathSocket);
                }
            } catch (IOException e) {
                throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                        + " with message\n" + e.getLocalizedMessage());
            }
        }
        String serializationForm = converterJson.serialization(wrapperSocket);
        CLightningLogger.getInstance().debug(TAG, "Request: \n" + serializationForm);
        try {
            this.outputStream.write(serializationForm.getBytes(ENCODING));
            this.outputStream.flush();
            CLightningLogger.getInstance().debug(TAG,"Run request");
        } catch (IOException e) {
            throw new ServiceException("Exception generated to doCall method of the class " + this.getClass().getSimpleName()
                    + " with message\n" + e.getLocalizedMessage());
        }
        Object o = converterJson.deserialization(inputStream, typeResult);
        //TODO I should be close the socket my maybe no?
        //this.close();
        CLightningLogger.getInstance().debug(TAG,"Response\n" + converterJson.serialization(o));
        return o;
    }

    //get and setter
    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
