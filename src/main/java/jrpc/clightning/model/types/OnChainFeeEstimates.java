package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

public class OnChainFeeEstimates {

  @Expose
  @SerializedName("opening_channel_satoshis")
  private BigInteger openingChannelSatoshis;

  @Expose
  @SerializedName("mutual_close_satoshis")
  private BigInteger mutualCloseSatoshis;

  @Expose
  @SerializedName("unilateral_close_satoshis")
  private BigInteger unilateralCloseSatoshis;

  @Expose
  @SerializedName("htlc_timeout_satoshis")
  private BigInteger htlcTimeoutSatoshis;

  @Expose
  @SerializedName("htlc_success_satoshis")
  private BigInteger htlcSuccessSatoshis;

  public BigInteger getOpeningChannelSatoshis() {
    return openingChannelSatoshis;
  }

  public BigInteger getMutualCloseSatoshis() {
    return mutualCloseSatoshis;
  }

  public BigInteger getUnilateralCloseSatoshis() {
    return unilateralCloseSatoshis;
  }

  public BigInteger getHtlcTimeoutSatoshis() {
    return htlcTimeoutSatoshis;
  }

  public BigInteger getHtlcSuccessSatoshis() {
    return htlcSuccessSatoshis;
  }
}
