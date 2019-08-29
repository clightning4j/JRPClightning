package jrpc.clightning.service.socket;

import jrpc.exceptions.ServiceException;
import jrpc.service.socket.UnixDomainSocketRpc;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningSocket extends UnixDomainSocketRpc{

    public CLightningSocket(String pathSocket) throws ServiceException {
        super(pathSocket);
    }

    public CLightningSocket() throws ServiceException {
        super(""); //TODO setting default configuration
    }


}
