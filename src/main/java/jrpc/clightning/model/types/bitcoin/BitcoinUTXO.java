package jrpc.clightning.model.types.bitcoin;

public class BitcoinUTXO {

  private String txId;
  private int index;

  public BitcoinUTXO(String txId, int index) {
    this.txId = txId;
    this.index = index;
  }

  public String getTxId() {
    return txId;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public String toString() {
    return String.format("%s: %d", txId, index);
  }
}
