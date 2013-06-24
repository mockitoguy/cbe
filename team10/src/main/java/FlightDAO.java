import java.util.HashMap;
import java.util.Map;

public class FlightDAO {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(String flightName, int count, Double defaultPrice) {
        flights.put(flightName, new Flight(count, defaultPrice));
    }

    public Flight getFlight(String flightName) {
        Flight flight = flights.get(flightName);

        if (flight == null)
            throw new NotExistingFlight();

        return flight;

    }
}
