package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningPay {

    @SerializedName("payment_preimage")
    private String paymentPreImage;
    @SerializedName("getroute_tries")
    private String getRouteTries;
    @SerializedName("sendpay_tries")
    private String sendPayTries;

    public String getPaymentPreImage() {
        return paymentPreImage;
    }

    public String getGetRouteTries() {
        return getRouteTries;
    }

    public String getSendPayTries() {
        return sendPayTries;
    }
}
