package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

public class CLightningPing {

  @SerializedName("totlen")
  private int totLen;

  public int getTotLen() {
    return totLen;
  }
}
