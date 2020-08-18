package jrpc.clightning.model.types;

import java.util.ArrayList;
import java.util.List;

public class CLightningListPay {

    private List<CLightningPay> pays = new ArrayList<>();

    public List<CLightningPay> getPays() {
        return pays;
    }
}
