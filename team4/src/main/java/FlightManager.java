import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.math.BigDecimal;
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

    public BigDecimal findCheapestSeatPrice(String flighNo) {
        Flight flight = flights.get(flighNo);
        List<Seat> seats = flight.getSeats();

        Collections.sort(seats, new Comparator<Seat>() {
            @Override
            public int compare(Seat seat1, Seat seat2) {
                return seat1.getPrice().compareTo(seat2.getPrice());
            }
        });
        return seats.get(0).getPrice();

    }

    public Iterable<Flight> findFlightsBetween(final String origin, final String destination) {
        Predicate<Flight> between = new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flight.getOrigin().equals(origin) && flight.getDestination().equals(destination);
            }
        };

        return Iterables.filter(flights.values(), between);
    }

    public Iterable<Flight> findFlightsFrom(final String origin) {
        Predicate<Flight> from = new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flight.getOrigin().equals(origin);
            }
        };

        return Iterables.filter(flights.values(), from);
    }
}
