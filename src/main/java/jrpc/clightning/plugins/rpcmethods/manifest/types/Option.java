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
    protected boolean deprecated = false;

    public Option() {
    }

    public Option(JsonObject object) {
        this.namePlugin = object.get("name").getAsString();
        this.descriptionPlugin = object.get("type").getAsString();
        this.type = object.get("default").getAsString();
        this.defaultPropriety = object.get("description").getAsString();
        this.deprecated = object.get("deprecated").getAsBoolean();
    }

    public String getNamePlugin() {
        return namePlugin;
    }

    public void setName(String namePlugin) {
        this.namePlugin = namePlugin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultPropriety() {
        return defaultPropriety;
    }

    public void setDefaultValue(String defaultPropriety) {
        this.defaultPropriety = defaultPropriety;
    }

    public void setDescriptionOption(String descriptionPlugin) {
        this.descriptionPlugin = descriptionPlugin;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
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
