package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class CLightningReservation {

    @SerializedName("txid")
    private String txId;
    private int vout;
    @SerializedName("was_reserved")
    private boolean wasReserved;
    private boolean reserved;
    @SerializedName("reserved_to_block")
    private BigInteger blockReservation;

    public String getTxId() {
        return txId;
    }

    public int getVout() {
        return vout;
    }

    public boolean isWasReserved() {
        return wasReserved;
    }

    public boolean isReserved() {
        return reserved;
    }

    public BigInteger getBlockReservation() {
        return blockReservation;
    }
}
