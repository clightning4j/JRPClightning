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

  @Expose
  @SerializedName("short_channel_id")
  private String shortChannelId;

  @Expose
  @SerializedName("public")
  private boolean publicChannel;

  @Expose private long satoshis;

  @Expose
  @SerializedName("amount_msat")
  private String amountMilliSat;

  @Expose
  @SerializedName("message_flags")
  private int messageFlags;

  @Expose
  @SerializedName("channel_flags")
  private int channelFlags;

  @Expose private boolean active;

  @Expose
  @SerializedName("last_update")
  private Date lastUpdate;

  @Expose
  @SerializedName("base_fee_millisatoshi")
  private BigInteger baseFeeMilliSatoshi;

  @Expose
  @SerializedName("fee_per_millionth")
  private long feePerMillionth;

  @Expose private long delay;

  @Expose
  @SerializedName("htlc_minimum_msat")
  private String htlcMinimumMSat;

  @Expose
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

  @Deprecated
  public String getAmountSat() {
    return this.getAmountMilliSat();
  }

  public String getAmountMilliSat() {
    return amountMilliSat;
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
