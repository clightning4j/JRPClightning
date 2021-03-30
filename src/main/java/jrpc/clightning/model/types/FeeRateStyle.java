package jrpc.clightning.model.types;

public enum FeeRateStyle {
  PERKW("perkw"),
  PERKB("perkb");

  private String style;

  FeeRateStyle(String style) {
    this.style = style;
  }

  @Override
  public String toString() {
    return style;
  }
}
