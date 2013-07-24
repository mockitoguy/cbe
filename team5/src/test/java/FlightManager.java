import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightManager {

    private HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();
    private int index = 1;

    public void addFlight(Flight flight) {
        flights.put(index++, flight);
    }

    public List<Flight> getFlight(String origin, String destination) {
        List<Flight> flight = new ArrayList<Flight>();
        for (int i = 1; i <= flights.size(); i++) {
            if (flights.get(i).getOrigin() == origin && flights.get(i).getDestination() == destination) {
                flight.add(flights.get(i));
            }
        }
        return flight;
    }

    public List<Flight> getFlight(String origin) {
        List<Flight> flight = new ArrayList<Flight>();
        for (int i = 1; i <= flights.size(); i++) {
            if (flights.get(i).getOrigin() == origin) {
                flight.add(flights.get(i));
            }
        }
        return flight;
    }

    public List<Flight> getFlightWithDestination(String destination) {
        List<Flight> flight = new ArrayList<Flight>();
        for (int i = 1; i <= flights.size(); i++) {
            if (flights.get(i).getDestination() == destination) {
                flight.add(flights.get(i));
            }
        }
        return flight;
    }

}
