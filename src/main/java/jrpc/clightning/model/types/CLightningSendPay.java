package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CLightningSendPay {

    private String id;
    @SerializedName("payment_hash")
    private String paymentHash;
    private String destination;
    @SerializedName("amount_msat")
    private String amountMsat;
    @SerializedName("created_at")
    private Date createdAt;
    private String status;
    @SerializedName("payment_preimage")
    private String preimage;
    private String label;
    private String bolt11;

    public String getId() {
        return id;
    }

    public String getPaymentHash() {
        return paymentHash;
    }

    public String getDestination() {
        return destination;
    }

    public String getAmountMsat() {
        return amountMsat;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPreimage() {
        return preimage;
    }

    public String getLabel() {
        return label;
    }

    public String getBolt11() {
        return bolt11;
    }
}
