package jrpc.clightning.plugins.log;

public enum CLightningLevelLog {
    UNUSUAL("unusual"),
    DEBUG("debug"),
    IO("io"),
    INFO("info");

    private String level;

    CLightningLevelLog(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
