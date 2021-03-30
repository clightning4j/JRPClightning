package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningSendPay;

public class CLightningListSendPays {

  private List<CLightningSendPay> payments = new ArrayList<>();

  public List<CLightningSendPay> getPayments() {
    return payments;
  }
}
