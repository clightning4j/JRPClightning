package jrpc.clightning.plugins.log;

//TODO this is used inside the plugin and I need to refactoring and make this internal
//Only the plugin should be know the log level!
public enum CLightningLevelLog {
    INFO("info"),
    DEBUG("debug"),
    WARNING("warn"),
    ERROR("error");

    private String level;

    CLightningLevelLog(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
