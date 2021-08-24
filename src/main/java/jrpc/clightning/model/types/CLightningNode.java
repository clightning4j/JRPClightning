package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;
import java.util.List;

public class CLightningNode {

  @Expose
  @SerializedName("nodeid")
  private String nodeId;

  @Expose private String alias;
  @Expose private String color;

  @Expose
  @SerializedName("last_timestamp")
  private BigInteger lastTimestamp;

  @Expose private String features;
  @Expose private List<NetworkAddress> addresses;

  // getter
  public String getNodeId() {
    return nodeId;
  }

  public String getAlias() {
    return alias;
  }

  public String getColor() {
    return color;
  }

  public BigInteger getLastTimestamp() {
    return lastTimestamp;
  }

  public String getFeatures() {
    return features;
  }

  public List<NetworkAddress> getAddresses() {
    return addresses;
  }
}
