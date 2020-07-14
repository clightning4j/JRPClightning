package jrpc.clightning.model.types;

import java.util.ArrayList;
import java.util.List;

public class CLightningListPay {

    private List<CLightningPayment> pays = new ArrayList<>();

    public List<CLightningPayment> getPays() {
        return pays;
    }
}
