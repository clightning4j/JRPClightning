package jrpc.clightning.plugins.rpcmethods.manifest.types;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import jrpc.clightning.plugins.ICLightningPlugin;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class Option {

    @SerializedName("name")
    protected String namePlugin;
    protected String type = "string";
    @SerializedName("default")
    protected String defaultPropriety;
    @SerializedName("description")
    protected String descriptionPlugin;

    public Option() {}

    public Option(JsonObject object) {
        this.namePlugin = object.get("name").getAsString();
        this.descriptionPlugin = object.get("type").getAsString();
        this.type = object.get("default").getAsString();
        this.defaultPropriety = object.get("description").getAsString();
    }

    //getter method
    public String getNamePlugin() {
        return namePlugin;
    }

    public String getType() {
        return type;
    }

    public String getDefaultPropriety() {
        return defaultPropriety;
    }

    public String getDescriptionPlugin() {
        return descriptionPlugin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Options{");
        sb.append("name:'").append(namePlugin).append('\'');
        sb.append(", type:'").append(type).append('\'');
        sb.append(", def:'").append(defaultPropriety).append('\'');
        sb.append(", description:'").append(descriptionPlugin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
