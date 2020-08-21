package jrpc.clightning.model.types.bitcoin;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CLightningTransactionInput {

    @SerializedName("txid")
    private String txId;
    private int index;
    private BigInteger sequences;

    public String getTxId() {
        return txId;
    }

    public int getIndex() {
        return index;
    }

    public BigInteger getSequences() {
        return sequences;
    }
}
