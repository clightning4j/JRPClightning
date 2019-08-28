package jrpc.mock;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class RequestLightningRPC {

    private String method;

    private String argument;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getMethod() {
        return method;
    }

    public String getArgument() {
        return argument;
    }
}
