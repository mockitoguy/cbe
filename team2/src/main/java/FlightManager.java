import java.util.HashMap;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }


    public int getAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight.getSeatsCapacity();
    }
}
