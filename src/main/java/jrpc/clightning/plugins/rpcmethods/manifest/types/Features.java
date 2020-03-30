package jrpc.clightning.plugins.rpcmethods.manifest.types;

public class Features {

    private String node;
    private String init;
    private String invoice;

    public Features() { }

    public Features(String node, String init, String invoice) {
        this.node = node;
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
}
