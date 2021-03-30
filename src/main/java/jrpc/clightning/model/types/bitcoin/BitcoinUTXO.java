package jrpc.clightning.model.types.bitcoin;

public class BitcoinUTXO {

  private String txId;
  private int index;

  public BitcoinUTXO(String txId, int index) {
    this.txId = txId;
    this.index = index;
  }

  @Override
  public String toString() {
    return  txId + ": " + index;
  }
}
