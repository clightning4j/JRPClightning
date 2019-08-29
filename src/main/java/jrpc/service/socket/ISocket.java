package jrpc.service.socket;

import jrpc.exceptions.ServiceException;
import jrpc.wrapper.IWrapperSocketCall;

import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ISocket {

    Object doCall(IWrapperSocketCall wrapperSocket, Type typeResult) throws ServiceException;
}
