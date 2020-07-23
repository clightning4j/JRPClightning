package jrpc.clightning.model;

import jrpc.clightning.model.types.Route;

import java.util.ArrayList;
import java.util.List;

public class CLightningGetRoutes {

    private List<Route> routes = new ArrayList<>();

    public List<Route> getRoutes() {
        return routes;
    }
}
