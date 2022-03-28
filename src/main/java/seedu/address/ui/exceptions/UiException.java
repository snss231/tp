package seedu.address.ui.exceptions;

public class UiException extends Exception {
    public UiException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UiException} with the specified detail {@code message} and {@code cause}.
     */
    public UiException(String message, Throwable cause) {
        super(message, cause);
    }
}
