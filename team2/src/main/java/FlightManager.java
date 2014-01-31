import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight flight) {
        //TODO: check not nulls
        if (flights.get(flight.getFlightNumber()) != null) {
            throw new FlightAlreadyExistsException("Flight already exists" + flight.getFlightNumber());
        }
        flights.put(flight.getFlightNumber(), flight);
    }


    public int getAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight.getSeatsCapacity();
    }

    public void setPrice(String flightNumer, int seatNumber, Money value) {
        Flight flight  = flights.get(flightNumer);
        flight.seatAt(seatNumber).setValue(value);
    }

    public List<Seat> getCheapestSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        return flight.getCheapestSeats();
    }

}
