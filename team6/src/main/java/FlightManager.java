import java.util.HashMap;
import java.util.Map;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String,Flight>();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNo(), flight);
    }

    public int getAvailableSeats(String flightNo) throws NoSuchFlightException {
        Flight flight = flights.get(flightNo);

        if (flight == null) {
            throw new NoSuchFlightException();
        }

        return flight.getAvailableSeats();
    }

    public Seat bookSeat(String flightNo, String seatNo) throws NoSeatsAvailableException, NoSuchSeatAvailableException {
        Flight flight = flights.get(flightNo);

        return flight.bookSeat(seatNo);
    }
}
