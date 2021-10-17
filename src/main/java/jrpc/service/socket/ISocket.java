/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.service.socket;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.socket.IWrapperSocketCall;

/** @author https://github.com/vincenzopalazzo */
public interface ISocket {

  /**
   * Make a raw call to the socket without JSON parsing
   *
   * @param wrapperSocket: The Payload to encoding and send throw the socket.
   * @param typeResult: The type result where the JSON payload will be encoded
   * @return Object of the required type build with the JSON string content.
   * @throws SocketException Throws if any error with the socket will happen.
   */
  @Deprecated
  Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult)
      throws ServiceException, IOException;

  <T> T makeCall(IWrapperSocketCall wrapperSocketCall, Class<T> typeResult) throws ServiceException;
  /**
   * Make a raw call to the socket without JSON parsing
   *
   * @param wrapperSocket: The Payload to encoding and send throw the socket.
   * @return A raw JSON string
   * @throws SocketException Throws if any error with the socket will happen.
   */
  String doRawCall(IWrapperSocketCall wrapperSocket) throws SocketException, IOException;

  void close() throws ServiceException;

  boolean isOpen();

  int getReceiveBufferSize() throws SocketException;
}
