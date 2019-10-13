package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningChannelData {

    private String source;
    private String destination;
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
