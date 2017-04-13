package de.molit.xto;

/**
 * Created by Daniel Zsebedits on 13.04.2017.
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
