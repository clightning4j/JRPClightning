package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningPayment;

public class CLightningListSendPays {

    private List<CLightningPayment> payments = new ArrayList<>();

    public List<CLightningPayment> getPayments() {
        return payments;
    }
}
