package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CLightningPing {

  @Expose
  @SerializedName("totlen")
  private int totLen;

  public int getTotLen() {
    return totLen;
  }
}
