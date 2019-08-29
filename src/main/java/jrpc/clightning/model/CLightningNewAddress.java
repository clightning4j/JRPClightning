package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 *///
public class CLightningNewAddress {

    private String address;
    private String bech32;
    @SerializedName("p2sh-segwit")
    private String p2shSegwit;

    public String getP2shSegwit() {
        return p2shSegwit;
    }

    public String getAddress() {
        return address;
    }

    public String getBech32() {
        return bech32;
    }
}
