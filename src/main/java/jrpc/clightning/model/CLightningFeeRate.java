package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import jrpc.clightning.model.types.FeeRateInfo;
import jrpc.clightning.model.types.OnChainFeeEstimates;

public class CLightningFeeRate {

  private String type;
  @Expose private FeeRateInfo feeRateInfo;
  @Expose private OnChainFeeEstimates onChainFeeEstimates;

  public FeeRateInfo getFeeRateInfo() {
    return feeRateInfo;
  }

  public void setFeeRateInfo(FeeRateInfo feeRateInfo) {
    this.feeRateInfo = feeRateInfo;
  }

  public OnChainFeeEstimates getOnChainFeeEstimates() {
    return onChainFeeEstimates;
  }

  public void setOnChainFeeEstimates(OnChainFeeEstimates onChainFeeEstimates) {
    this.onChainFeeEstimates = onChainFeeEstimates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
