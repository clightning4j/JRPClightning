package jrpc.clightning.model.types;

import com.google.gson.JsonObject;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;


public class CLightningPluginConfModel {

    private String path;
    private String name;
    //TODO review the type of options
    private JsonObject options;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public CLightningJsonObject getOptions() {
        return new CLightningJsonObject(options);
    }
}
