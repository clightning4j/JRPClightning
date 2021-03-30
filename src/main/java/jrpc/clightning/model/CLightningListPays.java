package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningPay;

public class CLightningListPays {

  private List<CLightningPay> pays = new ArrayList<>();

  public List<CLightningPay> getPays() {
    return pays;
  }
}
