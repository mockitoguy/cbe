import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<>();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNo(), flight);
    }

    public int getAvailableSeats(String flightNo) throws FlighNotFoundException {
        if (!flights.containsKey(flightNo)) {
            throw new FlighNotFoundException("Flight " + flightNo + " not found");
        }
        return flights.get(flightNo).getAvailableSeatCount();
    }

    public Seat findCheapestSeat(String flighNo) {
        Flight flight = flights.get(flighNo);
        List<Seat> seats = flight.getSeats();

        Collections.sort(seats, new Comparator<Seat>() {
            @Override
            public int compare(Seat seat1, Seat seat2) {
                return seat1.getPrice().compareTo(seat2.getPrice());
            }
        });
        return seats.get(0);

    }

}
