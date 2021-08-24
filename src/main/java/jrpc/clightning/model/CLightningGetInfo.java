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
package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.NetworkAddress;

/** @author https://github.com/vincenzopalazzo */
public class CLightningGetInfo {

  @Expose private String id;
  @Expose private String alias;
  @Expose private String color;
  @Expose private int num_peers;
  @Expose private int num_pending_channels;
  @Expose private int num_active_channels;
  @Expose private int num_inactive_channels;
  @Expose private List<NetworkAddress> address = new ArrayList<>();
  @Expose private List<NetworkAddress> binding = new ArrayList<>();
  @Expose private String version;
  @Expose private Long blockheight;
  @Expose private String network;
  @Expose private Long msatoshi_fees_collected;
  @Expose private String fees_collected_msat;
  @Expose private String warning_lightningd_sync;

  public String getId() {
    return id;
  }

  public String getAlias() {
    return alias;
  }

  public String getColor() {
    return color;
  }

  public Integer getNum_peers() {
    return num_peers;
  }

  public Integer getNum_pending_channels() {
    return num_pending_channels;
  }

  public Integer getNum_active_channels() {
    return num_active_channels;
  }

  public Integer getNum_inactive_channels() {
    return num_inactive_channels;
  }

  public List<NetworkAddress> getAddress() {
    return address;
  }

  public List<NetworkAddress> getBinding() {
    return binding;
  }

  public String getVersion() {
    return version;
  }

  public Long getBlockheight() {
    return blockheight;
  }

  public String getNetwork() {
    return network;
  }

  public Long getMsatoshi_fees_collected() {
    return msatoshi_fees_collected;
  }

  public String getFees_collected_msat() {
    return fees_collected_msat;
  }

  public String getWarning_lightningd_sync() {
    return warning_lightningd_sync;
  }
}
