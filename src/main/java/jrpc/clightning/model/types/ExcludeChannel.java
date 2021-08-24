package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;

public class ExcludeChannel {

  @Expose private String shortChannel;
  @Expose private int direction;

  public ExcludeChannel(String shortChannel, int direction) {
    this.shortChannel = shortChannel;
    this.direction = direction;
  }

  @Override
  public String toString() {
    return shortChannel.concat("/") + this.direction;
  }
}
