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
package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningPeerData {

    @SerializedName("id")
    private String idPeer;
    private Boolean connected;
    private List<String> netaddr;
    private String features;
    private List<CLightningChannelData> channels;
    private String log;

    public String getIdPeer() {
        return idPeer;
    }

    public Boolean getConnected() {
        return connected;
    }

    public List<String> getNetaddr() {
        return netaddr;
    }

    public List<CLightningChannelData> getChannels() {
        return channels;
    }

    public String getLog() {
        return log;
    }
}
