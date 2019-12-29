package jrpc.clightning.plugins.rpcmethods.init;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 */
class Configuration {

    @SerializedName("lightning-dir")
    private String socketPath;
    @SerializedName("rpc-file")
    private String socketName;
    private Boolean startup = Boolean.TRUE;

    public Configuration(String socketPath, String socketName, Boolean startup) {
        this.socketPath = socketPath;
        this.socketName = socketName;
        this.startup = startup;
    }

    //getter
    public String getSocketPath() {
        return socketPath;
    }

    public String getSocketName() {
        return socketName;
    }

    public Boolean getStartup() {
        return startup;
    }
}
