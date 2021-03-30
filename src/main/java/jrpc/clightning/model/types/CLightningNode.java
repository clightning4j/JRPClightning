package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;
import java.util.List;

public class CLightningNode {

  @SerializedName("nodeid")
  private String nodeId;

  private String alias;
  private String color;

  @SerializedName("last_timestamp")
  private BigInteger lastTimestamp;

  private String features;
  private List<NetworkAddress> addresses;

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
