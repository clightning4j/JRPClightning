package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.Route;

public class CLightningGetRoutes {

  @Expose private List<Route> routes = new ArrayList<>();

  public List<Route> getRoutes() {
    return routes;
  }
}
