package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningListSendPays {

    private long id;
    @SerializedName("payment_hash")
    private String paymentHash;
    private String destination;
    @SerializedName("amount_msat")
    private String amountMSat;
    @SerializedName("created_at")
    private Date createdAt;
    private String status;
    @SerializedName("payment_preimage")
    private String paymentPreImage;
    private String label;
    private String bolt11;

    public long getId() {
        return id;
    }

    public String getPaymentHash() {
        return paymentHash;
    }

    public String getDestination() {
        return destination;
    }

    public String getAmountMSat() {
        return amountMSat;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentPreImage() {
        return paymentPreImage;
    }

    public String getLabel() {
        return label;
    }

    public String getBolt11() {
        return bolt11;
    }
}
