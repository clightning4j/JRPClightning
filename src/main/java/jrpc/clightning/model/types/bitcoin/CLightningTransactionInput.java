package jrpc.clightning.model.types.bitcoin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class CLightningTransactionInput {

  @Expose
  @SerializedName("txid")
  private String txId;

  @Expose private int index;
  @Expose private BigInteger sequences;

  public String getTxId() {
    return txId;
  }

  public int getIndex() {
    return index;
  }

  public BigInteger getSequences() {
    return sequences;
  }
}
