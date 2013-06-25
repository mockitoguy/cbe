package com.example;

import java.math.BigDecimal;
import java.util.HashMap;
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
        return flight.getAvailableSeats();
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
}
