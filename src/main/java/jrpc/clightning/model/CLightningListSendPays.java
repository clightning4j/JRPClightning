package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningSendPay;

public class CLightningListSendPays {

  @Expose private List<CLightningSendPay> payments = new ArrayList<>();

  public List<CLightningSendPay> getPayments() {
    return payments;
  }
}
