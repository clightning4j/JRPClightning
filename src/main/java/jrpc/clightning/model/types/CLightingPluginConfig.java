/**
 * This is a wrapper for c-lightning RPC interface. Copyright (C) 2020 Vincenzo Palazzo
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
package jrpc.clightning.model.types;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/** @author https://github.com/vincenzopalazzo */
public class CLightingPluginConfig {

  @SerializedName("lightning-dir")
  private String lightningDir;

  @SerializedName("rpc-file")
  private String rpcFile;

  @SerializedName("startup")
  private Boolean startup;

  private String network;

  @SerializedName("feature_set")
  private JsonObject featureSet;

  private NetworkAddress proxy;

  @SerializedName("torv3-enabled")
  private boolean torv3;

  @SerializedName("use_proxy_always")
  private boolean useProxyAlways;

  public String getLightningDir() {
    return lightningDir;
  }

  public String getRpcFile() {
    return rpcFile;
  }

  public Boolean getStartup() {
    return startup;
  }

  public String getNetwork() {
    return network;
  }

  public JsonObject getFeatureSet() {
    return featureSet;
  }

  public NetworkAddress getProxy() {
    return proxy;
  }

  public boolean isTorv3() {
    return torv3;
  }

  public boolean isUseProxyAlways() {
    return useProxyAlways;
  }

  public boolean isProxyEnabled() {
    boolean proxyExist = proxy != null;
    return proxyExist && proxy.isValid();
  }
}
