package jrpc.clightning.plugins.exceptions;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningPluginException extends Exception{

    public CLightningPluginException() {
    }

    public CLightningPluginException(String message) {
        super(message);
    }

    public CLightningPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLightningPluginException(Throwable cause) {
        super(cause);
    }

    public CLightningPluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
