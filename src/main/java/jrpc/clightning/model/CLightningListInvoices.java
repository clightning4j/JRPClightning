package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningGetListInvoices {

    private List<CLightningInvoice> listInvoice = new ArrayList<>();

    public List<CLightningInvoice> getListInvoice() {
        return listInvoice;
    }
}
