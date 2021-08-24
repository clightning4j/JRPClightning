package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;

public class HelpItem {

  @Expose private String command;
  @Expose private String category;
  @Expose private String description;
  @Expose private String verbose;

  public String getCommand() {
    return command;
  }

  public String getCategory() {
    return category;
  }

  public String getDescription() {
    return description;
  }

  public String getVerbose() {
    return verbose;
  }
}
