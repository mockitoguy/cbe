/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 11:46
 */
public class FlightHasNoSeatsException extends RuntimeException {

    public FlightHasNoSeatsException(String flightNumber) {
        super("Flight "+flightNumber+" has not seats");
    }
}
