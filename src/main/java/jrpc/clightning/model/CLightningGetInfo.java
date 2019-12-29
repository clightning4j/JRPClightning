/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning.model;

import jrpc.clightning.model.types.NetworkAddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningGetInfo {

    private String id;
    private String alias;
    private String color;
    private int num_peers;
    private int num_pending_channels;
    private int num_active_channels;
    private int num_inactive_channels;
    private List<NetworkAddresses> address = new ArrayList<>(); //TODO the this
    private List<NetworkAddresses> binding = new ArrayList<>();
    private String version;
    private Long blockheight;
    private String network;
    private Long msatoshi_fees_collected;
    private String fees_collected_msat;
    private String warning_lightningd_sync;

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

    public List<NetworkAddresses> getAddress() {
        return address;
    }

    public List<NetworkAddresses> getBinding() {
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
