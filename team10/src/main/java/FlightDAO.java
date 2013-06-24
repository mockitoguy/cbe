import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private List<Flight> flights = new ArrayList<Flight>();

    public FlightDAO() {
    }

    public FlightDAO(List<Flight> flights) {
        this.flights = flights;
    }

    public int count(){
        return flights.size();
    }

    public Flight getFlight(String flightName) {
        for (Flight flight : flights) {
            if (flight.getName().equalsIgnoreCase(flightName))
                return flight;
        }
        throw new NotExistingFlight();

    }

    public List<Flight> findFlightBetween(String from, String to) {
        List<Flight> result = new ArrayList<Flight>();

        for (Flight flight : flights) {
            if (flight.getFrom().equals(from) && flight.getTo().equals(to)) {
                result.add(flight);
            }
        }
        return result;
    }
}
