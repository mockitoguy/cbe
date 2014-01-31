/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 11:38
 */
public class NoFlightFoundException extends RuntimeException {

    public NoFlightFoundException(String flightNumber) {
        super("No flight found with number ".concat(flightNumber));
    }
}
