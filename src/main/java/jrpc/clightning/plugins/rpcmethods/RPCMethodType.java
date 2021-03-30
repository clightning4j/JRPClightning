package jrpc.clightning.plugins.rpcmethods;

public enum RPCMethodType {
  HOOK("HOOK"),
  RPCMETHOD("RPCMETHOD");

  private String type;

  RPCMethodType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
