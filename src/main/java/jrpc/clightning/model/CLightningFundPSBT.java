package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import jrpc.clightning.model.types.CLightningReservation;

public class CLightningFundPSBT {

  @Expose private String psbt;

  @Expose
  @SerializedName("feerate_per_kw")
  private int feeratePerKw;

  @Expose
  @SerializedName("estimated_final_weight")
  private int estimatedFinalWeight;

  @Expose
  @SerializedName("excess_msat")
  private String excessMilliSat;

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

  public String getExcessMilliSat() {
    return excessMilliSat;
  }

  public List<CLightningReservation> getReservations() {
    return reservations;
  }
}
