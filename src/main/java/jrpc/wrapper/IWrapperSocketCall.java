package jrpc.wrapper;

import java.util.Map;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IWrapperSocketCall {

    int getId();

    String getMethod();

    Map<String, String> getParams();
}
