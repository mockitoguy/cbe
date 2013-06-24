package cbe.flights;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tskrobol
 */
public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightName(), flight);
    }

    public int getAvailableSeatsCount(String flightName) {
        if (!flights.containsKey(flightName))
            return 0;
        Integer seatsCount = flights.get(flightName).getMaxSeatsCount();
        return seatsCount;
    }

    public Seat getCheapestSeat(String flightName) {
        if (!flights.containsKey(flightName))
            return null;

        Seat seatsCount = flights.get(flightName).getCheapestSeat();
        return seatsCount;
    }

    public boolean bookSeatInFlight(String flightName, int seatID, User user) {
        Flight flight = flights.get(flightName);
        if (flight == null)
            return false;

        return flight.bookSeat(seatID, user);
    }

    public double getNonBookedAvaragePrice(String flightName) {
        Flight flight = flights.get(flightName);
        if (flight == null)
            return 0;

        return flight.getNotBookedAvaragePrice();
    }
}