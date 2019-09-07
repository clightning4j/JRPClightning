package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningBitcoinTx {

    @SerializedName("unsigned_tx")
    private String unsignedTx;
    private String txId;

    public String getUnsignedTx() {
        return unsignedTx;
    }

    public String getTxId() {
        return txId;
    }
}
