package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.bitcoin.CLightningRawTransactions;

public class CLightningListTransactions {

  @Expose private List<CLightningRawTransactions> transactions = new ArrayList<>();

  public List<CLightningRawTransactions> getTransactions() {
    return transactions;
  }
}
