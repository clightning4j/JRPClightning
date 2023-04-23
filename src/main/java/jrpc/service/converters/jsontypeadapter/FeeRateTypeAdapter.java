package jrpc.service.converters.jsontypeadapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import jrpc.clightning.model.CLightningFeeRate;
import jrpc.clightning.model.types.FeeRateInfo;
import jrpc.clightning.model.types.OnChainFeeEstimates;

import static java.util.Objects.requireNonNull;

public class FeeRateTypeAdapter extends TypeAdapter<CLightningFeeRate> {

  private final Gson gson;

  public FeeRateTypeAdapter(Gson gson) {
      this.gson = requireNonNull(gson);
  }

  @Override
  public void write(JsonWriter out, CLightningFeeRate value) throws IOException {
    out.beginObject();

    out.name(value.getType());
    gson.toJson(value.getFeeRateInfo(), FeeRateInfo.class, out);

    out.name("onchain_fee_estimates");
    gson.toJson(value.getOnChainFeeEstimates(), OnChainFeeEstimates.class, out);

    out.endObject();
  }

  @Override
  public CLightningFeeRate read(JsonReader in) throws IOException {
    CLightningFeeRate feeRate = new CLightningFeeRate();
    in.beginObject();
    String fieldname = null;
    while (in.hasNext()) {
      JsonToken token = in.peek();

      if (token.equals(JsonToken.NAME)) {
        fieldname = in.nextName();
      }

      if (fieldname.equals("perkw") || fieldname.equals("perkb")) {
        // move to next token
        // token = in.peek();
        feeRate.setType(fieldname);
        FeeRateInfo info = gson.fromJson(in, FeeRateInfo.class);
        feeRate.setFeeRateInfo(info);
      }

      if (fieldname.equals("onchain_fee_estimates")) {
        // token = in.peek();
        OnChainFeeEstimates feeEstimates = gson.fromJson(in, OnChainFeeEstimates.class);
        feeRate.setOnChainFeeEstimates(feeEstimates);
      }
    }
    in.endObject();
    return feeRate;
  }
}
