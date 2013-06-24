package pl.codebyexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class FlightManager {

    private List<Flight> flights = new ArrayList<Flight>();

    public int getSeatCountForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getAvailableSeats();
            }
        }
        return 0;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public int getTheCheapestSeatPriceForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getCheapestSeat();
            }
        }
        return 0;
    }

    public String bookSeatForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.bookSeat();
            }
        }
        return null;
    }
}
