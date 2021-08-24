package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class CLightningReservation {

  @Expose
  @SerializedName("txid")
  private String txId;

  @Expose private int vout;

  @Expose
  @SerializedName("was_reserved")
  private boolean wasReserved;

  @Expose private boolean reserved;

  @Expose
  @SerializedName("reserved_to_block")
  private BigInteger blockReservation;

  public String getTxId() {
    return txId;
  }

  public int getVout() {
    return vout;
  }

  public boolean isWasReserved() {
    return wasReserved;
  }

  public boolean isReserved() {
    return reserved;
  }

  public BigInteger getBlockReservation() {
    return blockReservation;
  }
}
