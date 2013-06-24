package pl.codebyexample;

import java.math.BigDecimal;
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

    public BigDecimal getTheCheapestSeatPriceForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getCheapestSeat();
            }
        }
        return BigDecimal.ZERO;
    }

    public String bookSeatForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.bookSeat();
            }
        }
        return null;
    }

    public BigDecimal getAvaragePriceOfAvailableSeatsForFlightNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight.getAvaragePriceOfAvailableSeats();
            }
        }
        return BigDecimal.ZERO;
    }

    public List<FlightInfo> flightsBetween(String origin, String destination) {
        List<FlightInfo> ret = new ArrayList<FlightInfo>();
        for (Flight flight : flights) {
            if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                ret.add(new FlightInfo(flight.getFlightNumber(), flight.getDate()));
            }
        }
        return ret;
    }
}
