package jrpc.clightning.model;


import jrpc.clightning.model.types.CLightningSendPay;

import java.util.ArrayList;
import java.util.List;


public class CLightningListSendPays {

    private List<CLightningSendPay> payments = new ArrayList<>();

    public List<CLightningSendPay> getPayments() {
        return payments;
    }
}
