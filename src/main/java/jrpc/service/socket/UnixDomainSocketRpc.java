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
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import jrpc.wrapper.socket.IWrapperSocketCall;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

public abstract class UnixDomainSocketRpc implements ISocket {

  private static final Class TAG = UnixDomainSocketRpc.class;
  protected static final String ENCODING = StandardCharsets.UTF_8.name();

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

  private AFUNIXSocket makeSocket() throws IOException {
    try {
      var socket = AFUNIXSocket.newInstance();
      socket.connect(AFUNIXSocketAddress.of(this.socketFile));
      return socket;
    } catch (IOException e) {
      throw e;
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
    CLightningLogger.getInstance().debug(TAG, String.format("Request: \n%s", serializationForm));
    AFUNIXSocket socket = null;
    OutputStream outputStream = null;
    try {
      socket = makeSocket();
      // Send the message
      outputStream = socket.getOutputStream();
      outputStream.write(serializationForm.getBytes(ENCODING));
      outputStream.flush();

      // receive the message
      var result = readAll(socket);
      CLightningLogger.getInstance().debug(TAG, String.format("Response: %s", result));
      return result;
    } catch (IOException exception) {
      throw exception;
    } finally {
      if (outputStream != null) outputStream.close();
      if (socket != null) socket.close();
    }
  }

  private String readAll(Socket socket) throws IOException {
    var inputStream = socket.getInputStream();
    Reader inputReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    var response = new StringBuilder();
    int buffSize = 1024;
    char[] buffer = new char[buffSize];

    int read;
    // FIXME(vincenzopalazzo): We can do better that this, because I think that indexOf take O(N)
    // time complexity, but it avoid the space complexity to make a toString() each time.
    // However, we can avoid it? Mh!
    while ((read = inputReader.read(buffer, 0, buffer.length)) > 0) {
      response.append(buffer, 0, read);
      // c-lightning add \n\n at the end of all rpc answer and JAVA have
      // same problem to catch it
      // Source https://github.com/ElementsProject/lightning/blob/master/lightningd/jsonrpc.c#L202
      if (response.indexOf("\n\n") != -1) {
        return response.toString();
      }
    }
    return response.toString();
  }
}
