package jrpc.clightning.model.types;

public class ExcludeChannel {

    private String shortChannel;
    private int direction;

    public ExcludeChannel(String shortChannel, int direction) {
        this.shortChannel = shortChannel;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return shortChannel.concat("/") + this.direction;
    }
}
