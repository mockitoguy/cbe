import java.util.HashMap;
import java.util.Map;

public class FlightManager {

    private Map<String, Integer> flights = new HashMap<String, Integer>();

    public void addFlight(String flightName, int count) {
        flights.put(flightName, count);
    }

    public int getAvailableSeatsCount(String name) {
        return flights.get(name);
    }
}
