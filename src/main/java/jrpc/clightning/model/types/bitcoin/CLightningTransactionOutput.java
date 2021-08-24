package jrpc.clightning.model.types.bitcoin;

import com.google.gson.annotations.Expose;

public class CLightningTransactionOutput {

  @Expose private int index;
  @Expose private String satoshis;
  @Expose private String scriptPubKey;

  public int getIndex() {
    return index;
  }

  public String getSatoshis() {
    return satoshis;
  }

  public String getScriptPubKey() {
    return scriptPubKey;
  }
}
