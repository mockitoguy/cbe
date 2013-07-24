package pl.payu.exceptions;

public class FlightNotExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public FlightNotExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FlightNotExistsException(String message) {
        super(message);
    }
}
