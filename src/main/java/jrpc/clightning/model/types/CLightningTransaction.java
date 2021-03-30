package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

// TODO maybe this class is redundant
public class CLightningTransaction {

  @SerializedName("txid")
  private String txId;

  private String tx;

  public String getTxId() {
    return txId;
  }

  public String getTx() {
    return tx;
  }
}
