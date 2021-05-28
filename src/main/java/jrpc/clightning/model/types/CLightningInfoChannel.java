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
package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

/** @author https://github.com/vincenzopalazzo */
public class CLightningInfoChannel {

  @SerializedName("peer_id")
  private String peerId;

  @SerializedName("short_channel_id")
  private String shortChannelId;

  @SerializedName("channel_sat")
  private BigInteger channelSat;

  @SerializedName("our_amount_msat")
  private String ourAmountMSat;

  @SerializedName("channel_total_sat")
  private BigInteger channelTotalSat;

  @SerializedName("amount_msat")
  private String amountMSat;

  @SerializedName("funding_txid")
  private String fundingTxId;

  @SerializedName("funding_output")
  private int fundingOutput;

  private Boolean connected;

  private String state;

  public String getPeerId() {
    return peerId;
  }

  public String getShortChannelId() {
    return shortChannelId;
  }

  public BigInteger getChannelSat() {
    return channelSat;
  }

  public String getOurAmountMSat() {
    return ourAmountMSat;
  }

  public BigInteger getChannelTotalSat() {
    return channelTotalSat;
  }

  public String getAmountMSat() {
    return amountMSat;
  }

  public int getFundingOutput() {
    return fundingOutput;
  }

  public String getFundingTxId() {
    return fundingTxId;
  }

  public Boolean getConnected() {
    return connected;
  }

  public String getState() {
    return state;
  }
}
