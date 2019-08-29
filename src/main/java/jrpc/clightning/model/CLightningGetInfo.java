package jrpc.clightning.model;

import jrpc.clightning.model.types.BindingType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningGetInfo {

    private String id;
    private String alias;
    private String color;
    private Integer num_peers;
    private Integer num_pending_channels;
    private Integer num_active_channels;
    private Integer num_inactive_channels;
    private List<String> address = new ArrayList<>();
    private List<BindingType> binding = new ArrayList<>();
    private String version;
    private Long blockheight;
    private String network;
    private Long msatoshi_fees_collected;
    private String fees_collected_msat;
    private String warning_lightningd_sync;

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getColor() {
        return color;
    }

    public Integer getNum_peers() {
        return num_peers;
    }

    public Integer getNum_pending_channels() {
        return num_pending_channels;
    }

    public Integer getNum_active_channels() {
        return num_active_channels;
    }

    public Integer getNum_inactive_channels() {
        return num_inactive_channels;
    }

    public List<String> getAddress() {
        return address;
    }

    public List<BindingType> getBinding() {
        return binding;
    }

    public String getVersion() {
        return version;
    }

    public Long getBlockheight() {
        return blockheight;
    }

    public String getNetwork() {
        return network;
    }

    public Long getMsatoshi_fees_collected() {
        return msatoshi_fees_collected;
    }

    public String getFees_collected_msat() {
        return fees_collected_msat;
    }

    public String getWarning_lightningd_sync() {
        return warning_lightningd_sync;
    }
}
