package jrpc.clightning.plugins.rpcmethods.manifest.types;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class Option {

    private String name;
    private String type;
    @SerializedName("default")
    private String def;
    private String description;

    public Option() {}

    public Option(String name, String type, String def, String description) {
        this.name = name;
        this.type = type;
        this.def = def;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDef() {
        return def;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Options{");
        sb.append("name:'").append(name).append('\'');
        sb.append(", type:'").append(type).append('\'');
        sb.append(", def:'").append(def).append('\'');
        sb.append(", description:'").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
