package jrpc.clightning.model;

import jrpc.clightning.model.types.CLightningPeerData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightninngListPeers {

    private List<CLightningPeerData> peers = new ArrayList<>();

    public List<CLightningPeerData> getPeers() {
        return peers;
    }
}
