package jrpc.clightning.model.types.bitcoin;

public class CLightningTransactionOutput {

  private int index;
  private String satoshis;
  private String scriptPubKey;

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
