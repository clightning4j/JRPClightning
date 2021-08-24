package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;

public class CLightningPSBT {

  @Expose private String psbt;

  public String getPSBT() {
    return psbt;
  }
}
