package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class CLightningSendPay {

  @Expose private String id;

  @Expose
  @SerializedName("payment_hash")
  private String paymentHash;

  @Expose private String destination;

  @Expose
  @SerializedName("amount_msat")
  private String amountMsat;

  @Expose
  @SerializedName("created_at")
  private Date createdAt;

  @Expose private String status;

  @Expose
  @SerializedName("payment_preimage")
  private String preimage;

  @Expose private String label;
  @Expose private String bolt11;

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
