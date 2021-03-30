package jrpc.clightning.model.types.bitcoin;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class CLightningRawTransactions {

  private String hash;
  private String rawtx;

  @SerializedName("blockheight")
  private int blockHeight;

  @SerializedName("txindex")
  private int txIndex;

  @SerializedName("locktime")
  private int locktime;

  private int version;
  private List<CLightningTransactionInput> inputs = new ArrayList<>();
  private List<CLightningTransactionOutput> outputs = new ArrayList<>();

  public String getHash() {
    return hash;
  }

  public String getRawtx() {
    return rawtx;
  }

  public int getBlockHeight() {
    return blockHeight;
  }

  public int getTxIndex() {
    return txIndex;
  }

  public int getLocktime() {
    return locktime;
  }

  public int getVersion() {
    return version;
  }

  public List<CLightningTransactionInput> getInputs() {
    return inputs;
  }

  public List<CLightningTransactionOutput> getOutputs() {
    return outputs;
  }
}
