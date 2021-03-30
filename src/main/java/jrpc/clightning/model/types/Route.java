package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

public class Route {

  private String id;

  @SerializedName("msatoshi")
  private String mSatoshi;

  @SerializedName("amount_msat")
  private String amountMsat;

  private int delay;
  private String style;

  public String getId() {
    return id;
  }

  public String getmSatoshi() {
    return mSatoshi;
  }

  public String getAmountMsat() {
    return amountMsat;
  }

  public int getDelay() {
    return delay;
  }

  public String getStyle() {
    return style;
  }
}
