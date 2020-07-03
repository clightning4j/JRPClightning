package jrpc.clightning.plugins.log;

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
