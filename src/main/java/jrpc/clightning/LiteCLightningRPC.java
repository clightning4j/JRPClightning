/**
 * This is a wrapper for c-lightning RPC interface. Copyright (C) 2020-2021 Vincenzo Palazzo
 * vincenzopalazzodev@gmail.com
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package jrpc.clightning;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.wrapper.socket.IWrapperSocketCall;
import jrpc.wrapper.socket.RPCUnixRequestMethod;

/**
 * Lite version of the client, this avoid all the complexity given by the design patter under the
 * hood.
 *
 * <p>In particular this call, give the possibility from the file path to create a proxy, and send
 * message over the unix socket. In addition, this class include only a generic method. If you want
 * to use an interface with all method and Java wrapper object you should use CLightningRPC.
 *
 * @author https://github.com/vincenzopalazzo
 */
public class LiteCLightningRPC {

  // The path where the socket it is located
  protected String path;
  // The socket object where it is possible talk with c-lightning.
  protected CLightningSocket socket;

  /**
   * Create a client where with a connection with the unix socket at the specified path
   *
   * @param path: String that contains the path where the socket it is located
   */
  public LiteCLightningRPC(String path) {
    this.path = path;
    this.socket = new CLightningSocket(path);
  }

  /**
   * Create a client where the configuration it is taken from a config file. TODO: Give the
   * opportunity to use also the os env.
   */
  public LiteCLightningRPC() {
    this.path = CLightningConfigurator.getInstance().getUrl();
    this.socket = new CLightningSocket();
  }

  public String getPath() {
    return path;
  }

  /**
   * Make a JSON RPC 2.0 call over the socket, and unwrap the result in a class of the specified
   * type
   *
   * @param methodName: The method name to be call.
   * @param type: The type of the object where the response need to be decoded.
   * @param <T>: The return type.
   * @return An object of the specified type with the content of the response.
   */
  public <T> T call(String methodName, Class<T> type) {
    return this.call(methodName, new HashMap<>(), type);
  }

  /**
   * Make a JSON RPC 2.0 call over the socket, and unwrap the result in a class of the specified
   * type
   *
   * @param methodName: The method name to be call.
   * @param params: A map where the parameter for the method call are specified.
   * @param type: The type of the object where the response need to be decoded.
   * @param <T>: The return type.
   * @return An object of the specified type with the content of the response.
   */
  public <T> T call(String methodName, Map<String, Object> params, Class<T> type) {
    IWrapperSocketCall request = new RPCUnixRequestMethod(methodName, params);
    return socket.makeCall(request, type);
  }

  /**
   * Low lever call, useful if you want useful another type of JSON decoder, it returns a raw string
   * received from the socket.
   *
   * @param method: The method name to be call.
   * @return Raw String returned from the socket.
   * @throws IOException In case of error
   */
  public String rawCall(String method) throws IOException {
    return this.rawCall(method, new HashMap<>());
  }

  /**
   * Low lever call, useful if you want useful another type of JSON decoder, it returns a raw string
   * received from the socket.
   *
   * @param method: The method name to be call.
   * @param params: A map where the parameter for the method call are specified.
   * @return Raw String returned from the socket.
   * @throws IOException In case of error
   */
  public String rawCall(String method, Map<String, Object> params) throws IOException {
    IWrapperSocketCall request = new RPCUnixRequestMethod(method, params);
    return socket.doRawCall(request);
  }
}
