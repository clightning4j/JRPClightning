package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

public class CLightningPay {

    private String bolt11;
    private String status;
    private String preimage;
    @SerializedName("amount_sent_msat")
    private String amountSent;

    public String getBolt11() {
        return bolt11;
    }

    public String getStatus() {
        return status;
    }

    public String getPreimage() {
        return preimage;
    }

    public String getAmountSent() {
        return amountSent;
    }
}
