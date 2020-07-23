package jrpc.clightning.model;

import jrpc.clightning.model.types.CLightningNode;

import java.util.ArrayList;
import java.util.List;

public class CLightningListNodes {

    private List<CLightningNode> nodes = new ArrayList<>();

    public List<CLightningNode> getNodes() {
        return nodes;
    }
}
