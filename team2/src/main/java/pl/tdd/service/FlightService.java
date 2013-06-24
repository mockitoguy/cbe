package pl.tdd.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * User: pcierpiatka
 */
public class FlightService {

    public static final int NO_SEATS = 0;
    private Map<String, Flight> flightRepo = new HashMap<>();

    public void addFlight(Flight flight) {
        flightRepo.put(flight.getFlightCode(), flight);
    }

    public int getAvailabeSeatsCount(String flightCode) {
        return getFlight(flightCode).getSeatsCount();
    }

    public double getPriceOfCheapestFlightSeat(String flightCode) {
        Flight flight = getFlight(flightCode);
        return flight.getCheapestSeatPrice();
    }

    private Flight getFlight(String flightCode) {
        Flight flight = flightRepo.get(flightCode);
        if(flight == null) {
            throw new UnknownFlightException();
        }
        return flight;
    }

    public boolean isFlightSeatAvailable(String flightCode, String seatNumber) {
        Flight flight = getFlight(flightCode);
        Seat seat = flight.getSeat(seatNumber);
        return seat.isAvailable();
    }

    public void book(String flightCode, String seatNumber) {
        Flight flight = getFlight(flightCode);
        flight.bookSeat(seatNumber);
    }

    public double getAveragePriceOfAvailableSeats(String flightCode) {
        Flight flight = getFlight(flightCode);
        return flight.getAveragePriceOfAvailableSeats();
    }
}
