package de.molit.xto;

/**
 * XTO representing an error,
 */
public class ErrorXTO {

    private String message;

    public ErrorXTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
