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
    this.path = CLightningConfigurator.getInstance().getSocketPath();
    this.socket = new CLightningSocket();
  }

  public String getPath() {
    return path;
  }

  public <T> T call(String methodName, Class<T> type) {
    return this.call(methodName, new HashMap<>(), type);
  }

  public <T> T call(String name, Map<String, Object> params, Class<T> type) {
    IWrapperSocketCall request = new RPCUnixRequestMethod(name, params);
    return (T) socket.doCall(request, type);
  }
}
