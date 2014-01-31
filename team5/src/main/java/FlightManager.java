import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:34
 */
public class FlightManager {

    private final Map<String, Flight> flights = new HashMap<>();

    public int getAvailableSeatsForFlight(String flightNumber) {
        return findFlight(flightNumber).getAvailableSeats();
    }

    public BigDecimal getCheapestSeatForFlight(String flightNumber) {
        return findFlight(flightNumber).getCheapestSeatPrice();
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getNumber(), flight);
    }

    public void bookSeat(String flightNumber, String seatNumber) {
        findFlight(flightNumber).bookSeat(seatNumber);
    }

    private Flight findFlight(String flightNumber) {
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber);
        } else {
            throw new NoFlightFoundException(flightNumber);
        }
    }
}
