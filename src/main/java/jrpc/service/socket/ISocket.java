package jrpc.service.socket;

import jrpc.wrapper.IWrapperSocketCall;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ISocket {

    Object doCall(IWrapperSocketCall wrapperSocket, Class typeResult);
}
