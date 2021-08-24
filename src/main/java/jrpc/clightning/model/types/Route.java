package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class Route {

  @Expose private String id;

  @Expose
  @SerializedName("msatoshi")
  private String mSatoshi;

  @Expose
  @SerializedName("amount_msat")
  private String amountMsat;

  @Expose private BigInteger delay;
  @Expose private String style;

  public String getId() {
    return id;
  }

  public String getmSatoshi() {
    return mSatoshi;
  }

  public String getAmountMsat() {
    return amountMsat;
  }

  public BigInteger getDelay() {
    return delay;
  }

  public String getStyle() {
    return style;
  }
}
