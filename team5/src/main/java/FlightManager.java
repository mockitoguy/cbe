import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: pgrela
 */
public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight ... flightsToAdd) {
        for (Flight flight : flightsToAdd) {
            if (flights.containsKey(flight.getFlightCode()))
                throw new RuntimeException("Flight already exists!");
            flights.put(flight.getFlightCode(), flight);
        }
    }

    public Flight getFlight(String flightCode) {
        if (!flights.containsKey(flightCode))
            throw new RuntimeException("No such flight!");
        return flights.get(flightCode);
    }

    public List<Flight> getFlightsBetween(String origin, String destination) {

        List<Flight> flightsBetween = new LinkedList<Flight>();
        for (Flight flight : flights.values()) {
            if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                flightsBetween.add(flight);
            }
        }
        return flightsBetween;
    }

    public List<Flight> getFlightsFrom(String origin) {

        List<Flight> flightsBetween = new LinkedList<Flight>();
        for (Flight flight : flights.values()) {
            if (flight.getOrigin().equals(origin)) {
                flightsBetween.add(flight);
            }
        }
        return flightsBetween;
    }

    public List<Flight> getFlightsTo(String destination) {

        List<Flight> flightsBetween = new LinkedList<Flight>();
        for (Flight flight : flights.values()) {
            if (flight.getDestination().equals(destination)) {
                flightsBetween.add(flight);
            }
        }
        return flightsBetween;
    }
}
