/**
 * UnixDomainSocketRpc the base class to handle communication between JLightning and the c-lightning
 * node over the UnixDomainSocket
 *
 * <p>This file is basically a port of the pylightning python client library that comes with
 * c-lightning.
 *
 * <p>The Author of this Java Client library is Rene Pickhardt and Vincenzo Palazzo He also holds
 * the copyright of this file. The library is licensed with a BSD-style license. Have a look at the
 * LICENSE file.
 *
 * @author Rene Pickhardt If you like this library consider a donation via bitcoin or the lightning
 *     network at http://ln.rene-pickhardt.de
 * @author https://github.com/vincenzopalazzo vincenzopalazzo apported the modific from this pull
 *     request https://github.com/ElementsProject/lightning/pull/2223
 */
package jrpc.service.socket;

import java.io.*;
import java.lang.reflect.Type;
import java.net.SocketException;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import jrpc.wrapper.socket.IWrapperSocketCall;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

public abstract class UnixDomainSocketRpc implements ISocket {

  private static final Class TAG = UnixDomainSocketRpc.class;
  protected static final String ENCODING = "UTF-8";

  protected IConverter converterJson;
  protected File socketFile;

  public UnixDomainSocketRpc(String pathSocket) {
    if (pathSocket == null || pathSocket.isEmpty()) {
      if (pathSocket != null) {
        throw new ServiceException("Path socket is null");
      } else {
        throw new ServiceException("Path socket is empty");
      }
    }
    this.socketFile = new File(pathSocket);
    this.converterJson = new JsonConverter();
  }

  private AFUNIXSocket makeSocket() {
    try {
      var socket = AFUNIXSocket.newInstance();
      socket.connect(AFUNIXSocketAddress.of(this.socketFile));
      return socket;
    } catch (IOException e) {
      var message =
          String.format(
              "Exception generated to doCall method of the class %s, with message: %s",
              this.getClass().getSimpleName(), e.getLocalizedMessage());
      CLightningLogger.getInstance().error(TAG, message);
      throw new ServiceException(message);
    }
  }

  @Override
  public int getReceiveBufferSize() throws SocketException {
    return -1;
  }

  @Override
  public void close() throws ServiceException {}

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult) throws ServiceException {
    if (wrapperSocket == null) {
      throw new IllegalArgumentException("The argument wrapperSocket is null");
    }

    try {
      var resultStr = this.doRawCall(wrapperSocket);
      return converterJson.deserialization(resultStr, typeResult);
    } catch (IOException e) {
      var errorMessage =
          String.format(
              "Error during call %s with message %s",
              wrapperSocket.getMethod(), e.getLocalizedMessage());
      throw new ServiceException(errorMessage, e.getCause());
    }
  }

  @Override
  public String doRawCall(IWrapperSocketCall wrapperSocket) throws IOException {
    if (wrapperSocket == null) {
      throw new IllegalArgumentException("The argument wrapperSocket is null");
    }

    String serializationForm = converterJson.serialization(wrapperSocket);
    CLightningLogger.getInstance().debug(TAG, "Request: \n" + serializationForm);
    var socket = makeSocket();
    // Send the message
    OutputStream outputStream = socket.getOutputStream();
    outputStream.write(serializationForm.getBytes(ENCODING));
    outputStream.flush();

    // receive the message
    InputStream inputStream = socket.getInputStream();
    var result = new String(inputStream.readAllBytes());
    CLightningLogger.getInstance().debug(TAG, "Response\n" + converterJson.serialization(result));
    socket.close();
    return result;
  }
}
