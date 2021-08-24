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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;
import java.util.Date;

/** @author https://github.com/vincenzopalazzo */
public class CLightningChannel {

  @Expose private String source;
  @Expose private String destination;

  @SerializedName("short_channel_id")
  private String shortChannelId;

  @SerializedName("public")
  private boolean publicChannel;

  private long satoshis;

  @SerializedName("amount_sat")
  private String amountSat;

  @SerializedName("message_flags")
  private int messageFlags;

  @SerializedName("channel_flags")
  private int channelFlags;

  private boolean active;

  @SerializedName("last_update")
  private Date lastUpdate;

  @SerializedName("base_fee_millisatoshi")
  private BigInteger baseFeeMilliSatoshi;

  @SerializedName("fee_per_millionth")
  private long feePerMillionth;

  private long delay;

  @SerializedName("htlc_minimum_msat")
  private String htlcMinimumMSat;

  @SerializedName("htlc_maximum_msat")
  private String htlcMaximumMSat;

  public String getSource() {
    return source;
  }

  public String getDestination() {
    return destination;
  }

  public String getShortChannelId() {
    return shortChannelId;
  }

  public boolean isPublicChannel() {
    return publicChannel;
  }

  public long getSatoshis() {
    return satoshis;
  }

  public String getAmountSat() {
    return amountSat;
  }

  public int getMessageFlags() {
    return messageFlags;
  }

  public int getChannelFlags() {
    return channelFlags;
  }

  public boolean isActive() {
    return active;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public BigInteger getBaseFeeMilliSatoshi() {
    return baseFeeMilliSatoshi;
  }

  public long getFeePerMillionth() {
    return feePerMillionth;
  }

  public long getDelay() {
    return delay;
  }

  public String getHtlcMinimumMSat() {
    return htlcMinimumMSat;
  }

  public String getHtlcMaximumMSat() {
    return htlcMaximumMSat;
  }
}
