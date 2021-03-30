package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class FeeRateInfo {

  private BigInteger opening;

  @SerializedName("mutual_close")
  private BigInteger mutualClose;

  @SerializedName("unilateral_close")
  private BigInteger unilateralClose;

  @SerializedName("delayed_to_us")
  private BigInteger delayToUs;

  @SerializedName("htlc_resolution")
  private BigInteger htlcResolution;

  private BigInteger penalty;

  @SerializedName("min_acceptable")
  private BigInteger minAcceptable;

  @SerializedName("max_acceptable")
  private BigInteger maxAcceptable;

  private BigInteger urgent;
  private BigInteger normal;
  private BigInteger slow;

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
