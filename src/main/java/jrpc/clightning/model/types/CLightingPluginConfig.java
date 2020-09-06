package jrpc.clightning.model.types;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class CLightingPluginConfig {

    @SerializedName("lightning-dir")
    private String lightningDir;
    @SerializedName("rpc-file")
    private String rpcFile;
    @SerializedName("startup")
    private Boolean startup;
    private String network;
    @SerializedName("feature_set")
    private JsonObject featureSet;
    //TODO maybe is util have the the proxy value and the use-proxy-olwaise???

    public String getLightningDir() {
        return lightningDir;
    }

    public String getRpcFile() {
        return rpcFile;
    }

    public Boolean getStartup() {
        return startup;
    }

    public String getNetwork() {
        return network;
    }

    public JsonObject getFeatureSet() {
        return featureSet;
    }
}
