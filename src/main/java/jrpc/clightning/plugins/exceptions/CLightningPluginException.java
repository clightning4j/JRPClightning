package jrpc.clightning.plugins.exceptions;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningPluginException extends RuntimeException{

    private int code;
    private String errorMessage;

    public CLightningPluginException(int code, String message) {
        super(message);
        this.code = code;
        this.errorMessage = message;
    }

    public CLightningPluginException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
