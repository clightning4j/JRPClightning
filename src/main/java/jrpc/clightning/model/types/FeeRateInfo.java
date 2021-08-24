package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class FeeRateInfo {

  @Expose private BigInteger opening;

  @Expose
  @SerializedName("mutual_close")
  private BigInteger mutualClose;

  @Expose
  @SerializedName("unilateral_close")
  private BigInteger unilateralClose;

  @Expose
  @SerializedName("delayed_to_us")
  private BigInteger delayToUs;

  @Expose
  @SerializedName("htlc_resolution")
  private BigInteger htlcResolution;

  @Expose private BigInteger penalty;

  @Expose
  @SerializedName("min_acceptable")
  private BigInteger minAcceptable;

  @Expose
  @SerializedName("max_acceptable")
  private BigInteger maxAcceptable;

  @Expose private BigInteger urgent;
  @Expose private BigInteger normal;
  @Expose private BigInteger slow;

  // getter
  public BigInteger getOpening() {
    return opening;
  }

  public BigInteger getMutualClose() {
    return mutualClose;
  }

  public BigInteger getUnilateralClose() {
    return unilateralClose;
  }

  public BigInteger getDelayToUs() {
    return delayToUs;
  }

  public BigInteger getHtlcResolution() {
    return htlcResolution;
  }

  public BigInteger getPenalty() {
    return penalty;
  }

  public BigInteger getMinAcceptable() {
    return minAcceptable;
  }

  public BigInteger getMaxAcceptable() {
    return maxAcceptable;
  }

  public BigInteger getUrgent() {
    return urgent;
  }

  public BigInteger getNormal() {
    return normal;
  }

  public BigInteger getSlow() {
    return slow;
  }
}
