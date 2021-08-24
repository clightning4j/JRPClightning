package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CLightningPay {

  @Expose private String bolt11;

  @Expose
  @SerializedName("payment_hash")
  private String paymentHash;

  @Expose private String status;

  @Expose
  @SerializedName("payment_preimage")
  private String preimage;

  @Expose private String label;

  @Expose
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
