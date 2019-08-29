package jrpc.wrapper.response;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class RPCResponseWrapper<T> {

    private float jsonrpc;
    private int id;
    private T result;

    public float getJsonrpc() {
        return jsonrpc;
    }

    public int getId() {
        return id;
    }

    public T getResult() {
        return result;
    }
}
