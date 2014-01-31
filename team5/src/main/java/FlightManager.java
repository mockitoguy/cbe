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
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber).getAvailableSeats();
        } else {
            return 0;
        }
    }

    public BigDecimal getCheapestSeatForFlight(String flightNumber) {
        if (flights.containsKey(flightNumber)) {
            Flight flight = flights.get(flightNumber);
            return flight.getCheapestSeatPrice();
        }
        return null;
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getNumber(), flight);
    }
}
