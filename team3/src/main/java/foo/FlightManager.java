package foo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class FlightManager {
    private Map<String, Flight> flightInfos = new HashMap<String, Flight>();

    public int getAvailableSeats(String flightCode) {
        return getFlightInfo(flightCode).getAvailableSeats();
    }

    public float getCheapestSeat(String flightCode) {
        return getFlightInfo(flightCode).getCheapestSeat();
    }

    Flight getFlightInfo(String flightCode) {
        Flight info;
        if (flightInfos.containsKey(flightCode)) {
            info = flightInfos.get(flightCode);
        } else {
            throw new FlightNotFoundException();
        }
        return info;
    }

    public int bookSeat(String flightNumber) {
        return getFlightInfo(flightNumber).bookSeat();
    }

    public List<Flight> getFlightsFrom(final String origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin cannot be null");
        }

        return filterFlights(new FlightFilter() {
            @Override
            public boolean accepts(Flight flight) {
                return origin.equals(flight.getFrom());
            }
        });
    }

    public List<Flight> getFlightsTo(final String destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination cannot be null");
        }

        return filterFlights(new FlightFilter() {
            @Override
            public boolean accepts(Flight flight) {
                return destination.equals(flight.getTo());
            }
        });
    }

    public List<Flight> getFlights(final String from, final String to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Neither origin nor destination can be null");
        }
        return filterFlights(new FlightFilter() {
            @Override
            public boolean accepts(Flight flight) {
                return from.equals(flight.getFrom()) && to.equals(flight.getTo());
            }
        });
    }

    private List<Flight> filterFlights(FlightFilter filter) {
        List<Flight> results = new ArrayList<Flight>();
        for (Flight flight : flightInfos.values()) {
            if (filter.accepts(flight)) {
                results.add(flight);
            }
        }
        return results;
    }

    public void addFlight(Flight flight) {
        if (flight.getNumber() == null) {
            throw new IllegalArgumentException("Flight number cannot be null");
        }
        if (flightInfos.containsKey(flight.getNumber())) {
            throw new DuplicateFlightException();
        }

        flightInfos.put(flight.getNumber(), flight);
    }

    interface FlightFilter {
        boolean accepts(Flight flight);
    }
}
