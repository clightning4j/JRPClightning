package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.HelpItem;

public class CLightningHelp {

  @Expose
  @SerializedName("help")
  private List<HelpItem> helpItems = new ArrayList<>();

  public List<HelpItem> getHelpItems() {
    return helpItems;
  }
}
