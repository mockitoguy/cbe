import java.util.HashMap;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(String flightName, int count) {
        flights.put(flightName, new Flight(count));
    }

    public int getAvailableSeatsCount(String name) {
        return flights.get(name).getSeatsCount();
    }

    public void addSeatPrice(String flightName, int seat, double price) {
        flights.get(flightName).getSeats().get(seat).setPrice(price);
    }

    public double getSeatPrice(String flightName, int seat) {
        return flights.get(flightName).getSeats().get(seat).getPrice();
    }
}
