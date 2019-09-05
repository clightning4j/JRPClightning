package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningListInvoices {

    @SerializedName("invoices")
    private List<CLightningInvoice> listInvoice = new ArrayList<>();

    public List<CLightningInvoice> getListInvoice() {
        return listInvoice;
    }
}
