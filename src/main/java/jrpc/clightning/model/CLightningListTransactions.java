package jrpc.clightning.model;

import jrpc.clightning.model.types.bitcoin.CLightningRawTransactions;

import java.util.ArrayList;
import java.util.List;

public class CLightningListTransactions {

    private List<CLightningRawTransactions> transactions = new ArrayList<>();

    public List<CLightningRawTransactions> getTransactions() {
        return transactions;
    }
}
