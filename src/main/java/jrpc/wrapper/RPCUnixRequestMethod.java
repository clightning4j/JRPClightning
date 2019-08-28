package jrpc.wrapper;

import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class RPCUnixRequestMethod implements IWrapperSocketCall{

    private int id;
    private String method;
    private Map<String, String> params;

    public RPCUnixRequestMethod(int id, String method, Map<String, String> params) {
        this.id = id;
        this.method = method;
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
