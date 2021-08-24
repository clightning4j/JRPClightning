package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import java.util.List;
import jrpc.clightning.model.types.CLightningReservation;

public class CLightningReserveInputs {

  @Expose private List<CLightningReservation> reservation;

  public List<CLightningReservation> getReservation() {
    return reservation;
  }
}
