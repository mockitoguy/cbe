import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: pgrela
 */
public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight flight) {
         flights.put(flight.getFlightCode(),flight);
    }

    public Flight getFlight(String flightCode) {
        if(!flights.containsKey(flightCode))
            throw new RuntimeException("No such flight!");
        return flights.get(flightCode);
    }

    public List<Flight> getFlightsBetween(String origin, String destination) {

        List<Flight> flightsBetween= new LinkedList<Flight>();
        for(Flight flight: flights.values()){
            if(flight.getOrigin()==origin && flight.getDestination()==destination){
                flightsBetween.add(flight);
            }
        }
        return flightsBetween;
    }
}
