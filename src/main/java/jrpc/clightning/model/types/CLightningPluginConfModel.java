package jrpc.clightning.model.types;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

public class CLightningPluginConfModel {

  @Expose private String path;
  @Expose private String name;
  // TODO review the type of options
  @Expose private JsonObject options;

  public String getPath() {
    return path;
  }

  public String getName() {
    return name;
  }

  public CLightningJsonObject getOptions() {
    return new CLightningJsonObject(options);
  }
}
