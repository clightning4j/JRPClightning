package jrpc.clightning.exceptions;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningException extends RuntimeException{

    public CLightningException() {
    }

    public CLightningException(String message) {
        super(message);
    }

    public CLightningException(String message, Throwable cause) {
        super(message, cause);
    }

    public CLightningException(Throwable cause) {
        super(cause);
    }

    public CLightningException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
