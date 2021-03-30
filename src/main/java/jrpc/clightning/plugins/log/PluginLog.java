package jrpc.clightning.plugins.log;

public enum PluginLog {
  DEBUG("debug"),
  INFO("info"),
  WARNING("warn"),
  ERROR("error");

  private String level;

  PluginLog(String level) {
    this.level = level;
  }

  public String getLevel() {
    return level;
  }
}
