import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:34
 */
public class FlightManager {

    private final Map<String, Flight> flights = new HashMap<>();

    public int getAvailableSeatsForFlight(String flightNumber) {
        return findFlight(flightNumber).getAvailableSeats();
    }

    public BigDecimal getCheapestSeatForFlight(String flightNumber) {
        return findFlight(flightNumber).getCheapestSeatPrice();
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getNumber(), flight);
    }

    public void bookSeat(String flightNumber, String seatNumber) {
        findFlight(flightNumber).bookSeat(seatNumber);
    }

    public BigDecimal getAveragePriceOfNonBookedSeats(String flightNumber) {
        return findFlight(flightNumber).getAveragePrice();
    }

    private Flight findFlight(String flightNumber) {
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber);
        } else {
            throw new NoFlightFoundException(flightNumber);
        }
    }

    public List<Flight> getNumberOfFlights(final String origin, final String destination) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flight.matchOriginAndDestination(origin, destination);
            }
        }));
    }

    public List<Flight> getNumberOfFlightsForOrigin(final String origin) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flight.matchOrigin(origin);
            }
        }));
    }

    public List<Flight> getNumberOfFlightsToDestination(final String destination) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(Flight flight) {
                return flight.matchDestination(destination);
            }
        }));
    }




}
