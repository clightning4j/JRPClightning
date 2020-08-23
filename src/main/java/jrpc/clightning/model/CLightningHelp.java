package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;
import jrpc.clightning.model.types.HelpItem;

import java.util.ArrayList;
import java.util.List;

public class CLightningHelp {

    @SerializedName("help")
    private List<HelpItem> helpItems = new ArrayList<>();

    public List<HelpItem> getHelpItems() {
        return helpItems;
    }
}
