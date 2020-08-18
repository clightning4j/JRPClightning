package jrpc.clightning.model;

import jrpc.clightning.model.types.CLightningPay;

import java.util.ArrayList;
import java.util.List;

public class CLightningListPays {

    private List<CLightningPay> pays = new ArrayList<>();

    public List<CLightningPay> getPays() {
        return pays;
    }
}
