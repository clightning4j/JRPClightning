package jrpc.clightning.plugins.rpcmethods.manifest.types;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;


/**
 * @author https://github.com/vincenzopalazzo
 */
public class Option {

    @SerializedName("name")
    protected String nameOption;
    protected String type = "string";
    @SerializedName("default")
    protected String defaultPropriety;
    @SerializedName("description")
    protected String descriptionPlugin;
    protected boolean deprecated = false;

    public Option() {  }

    public Option(JsonObject object) {
        this.nameOption = object.get("name").getAsString();
        this.descriptionPlugin = object.get("type").getAsString();
        this.type = object.get("default").getAsString();
        this.defaultPropriety = object.get("description").getAsString();
        this.deprecated = object.get("deprecated").getAsBoolean();
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setName(String namePlugin) {
        this.nameOption = namePlugin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("null")){
            this.type = null;
        }else{
            this.type = type;
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return nameOption != null ? nameOption.equals(option.nameOption) : option.nameOption == null;
    }

    @Override
    public int hashCode() {
        return nameOption != null ? nameOption.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Options{");
        sb.append("name:'").append(nameOption).append('\'');
        sb.append(", type:'").append(type).append('\'');
        sb.append(", def:'").append(defaultPropriety).append('\'');
        sb.append(", description:'").append(descriptionPlugin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
