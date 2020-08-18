package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

public class CLightningPay {

    private String bolt11;
    @SerializedName("payment_hash")
    private String paymentHash;
    private String status;
    @SerializedName("payment_preimage")
    private String preimage;
    private String label;
    @SerializedName("amount_sent_msat")
    private String amountSentMsat;


    public String getBolt11() {
        return bolt11;
    }

    public String getPaymentHash() {
        return paymentHash;
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

    public String getAmountSentMsat() {
        return amountSentMsat;
    }
}
