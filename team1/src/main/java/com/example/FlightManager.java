package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights;

    public FlightManager() {
        flights = new HashMap<String, Flight>();
    }

    public Integer getAvailableSeats(String flightNumber) throws FlightManagerException {
        Flight flight = flights.get(flightNumber);
        if(flight==null) {
            throw new FlightManagerException("No such flight");
        }
        return flight.getAvailableSeats().size();
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public BigDecimal getLowestSeatPrice(String flightNumber) throws FlightManagerException {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            throw new FlightManagerException("No such flight");
        }
        return flight.getLowestSeatPrice();
    }

    public BookingStatus bookSeat(String flightNumber, Integer seatNumber) {
        Flight flight = flights.get(flightNumber);
        Seat seat = flight.getSeat(seatNumber);
        if (seat.isAvailable()){
            seat.setAvailable(false);
            return BookingStatus.SUCCESS;
        }
        else {
            return BookingStatus.FAIL;
        }

    }

    public BigDecimal getAveragePriceOfAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        List<Seat> availableSeats = flight.getAvailableSeats();
        BigDecimal totalPriceOfAvailableSeats = new BigDecimal("0.00");
        for (Seat availableSeat : availableSeats) {
            totalPriceOfAvailableSeats = totalPriceOfAvailableSeats.add(availableSeat.getPrice());
        }
        BigDecimal averagePrice = totalPriceOfAvailableSeats.divide(new BigDecimal(availableSeats.size()));
        return averagePrice;
    }

    public List<Flight> findFlights(String from, String to) {
        List <Flight> flights = new ArrayList<Flight>();

        return flights;
    }
}
