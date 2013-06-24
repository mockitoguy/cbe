package pl.team3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pawelb
 * Date: 24.06.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();


    public Flight getFlightByNumber(String flightNumber) {

        if (flights == null) {
            throw new IllegalStateException("No flights");
        }

        return flights.get(flightNumber);

    }

    public void addFlight(String flightNumber, Flight seats) {
        if (flights.get(flightNumber) != null) {
            throw new IllegalStateException("Flight: " + flightNumber + "exists ");
        }
        flights.put(flightNumber, seats);

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
}
