package jrpc.clightning.plugins.rpcmethods.manifest.types;

import com.google.gson.annotations.Expose;

public class Notification {
  @Expose private String method;

  public Notification(String method) {
    this.method = method;
  }

  public String getMethod() {
    return method;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Notification)) return false;

    Notification that = (Notification) o;

    return method != null ? method.equals(that.method) : that.method == null;
  }

  @Override
  public int hashCode() {
    return method != null ? method.hashCode() : 0;
  }
}
