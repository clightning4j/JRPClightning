package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.bitcoin.CLightningRawTransactions;

public class CLightningListTransactions {

  private List<CLightningRawTransactions> transactions = new ArrayList<>();

  public List<CLightningRawTransactions> getTransactions() {
    return transactions;
  }
}
