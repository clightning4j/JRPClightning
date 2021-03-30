package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import jrpc.clightning.model.types.CLightningReservation;

public class CLightningFundPSBT {

  private String psbt;

  @SerializedName("feerate_per_kw")
  private int feeratePerKw;

  @SerializedName("estimated_final_weight")
  private int estimatedFinalWeight;

  @SerializedName("excess_msat")
  private int excessMilliSat;

  private List<CLightningReservation> reservations;

  public String getPsbt() {
    return psbt;
  }

  public int getFeeratePerKw() {
    return feeratePerKw;
  }

  public int getEstimatedFinalWeight() {
    return estimatedFinalWeight;
  }

  public int getExcessMilliSat() {
    return excessMilliSat;
  }

  public List<CLightningReservation> getReservations() {
    return reservations;
  }
}
