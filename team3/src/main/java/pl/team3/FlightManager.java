package pl.team3;

import java.util.ArrayList;
import java.util.List;

public class FlightManager {
    private List<Flight> flights = new ArrayList<Flight>();


    public Flight getFlightByNumber(String flightNumber) {

        if (flights == null) {
            throw new IllegalStateException("No flights");
        }

        for (Flight flight : flights) {
            if (flight != null && flightNumber.equals(flight.getFlightName())) {
                return flight;
            }
        }
        throw new IllegalStateException("no flight");

    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public List<Seat> getSeatsByFlightNumber(String flightNumber) {
        Flight flight = getFlightByNumber(flightNumber);

        if (flight == null) {
            return new ArrayList<Seat>();
        }

        return flight.getSeats();

    }

    public List<Seat> getCheapestSeatForFlight(String flightNumber) {
        Flight flight = getFlightByNumber(flightNumber);


        if (flight == null) {
            return new ArrayList<Seat>();
        }

        return flight.getCheapestSeat();
    }

    public List<Flight> findFlights(String from, String to) {
        if (flights == null) {
            return new ArrayList<Flight>();
        }
        List<Flight> results = new ArrayList<Flight>();
        for (Flight flight: flights) {
            if(flight.isFromTo(from, to)) {
                results.add(flight);
            }
        }

        return results;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Flight> findFrom(String from) {
        return findFlights(from, null);
    }

    public List<Flight> findTo(String to) {
        return findFlights(null, to);
    }
}
