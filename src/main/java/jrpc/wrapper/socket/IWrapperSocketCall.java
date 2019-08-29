package jrpc.wrapper.socket;

import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IWrapperSocketCall {

    int getId();

    String getMethod();

    Map<String, String> getParams();
}
