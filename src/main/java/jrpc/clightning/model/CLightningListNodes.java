package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningNode;

public class CLightningListNodes {

  private List<CLightningNode> nodes = new ArrayList<>();

  public List<CLightningNode> getNodes() {
    return nodes;
  }
}
