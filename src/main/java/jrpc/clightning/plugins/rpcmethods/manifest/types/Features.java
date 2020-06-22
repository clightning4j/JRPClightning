package jrpc.clightning.plugins.rpcmethods.manifest.types;

public class Features {

    private String node;
    private String channel;
    private String init;
    private String invoice;

    public Features() {}

    public Features(String node, String channel, String init, String invoice) {
        this.node = node;
        this.channel = channel;
        this.init = init;
        this.invoice = invoice;
    }

    //getter
    public String getNode() {
        return node;
    }

    public String getInit() {
        return init;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getChannel() {
        return channel;
    }
}
