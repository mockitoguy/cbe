package com.ak.flight;

import com.ak.flight.exception.NoSuchFlightExpection;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class FlightManager {

  private Map<String, Flight> flights = Maps.newHashMap();

  public int getAvailableSeatsCount(String flightNumber) throws NoSuchFlightExpection {
    Flight flight = flights.get(flightNumber);
    if (flight == null) {
      throw new NoSuchFlightExpection();
    }
    return flight.getSeatsCount();
  }

  public void addFlight(Flight flight) {
    flights.put(flight.getFlightNumber(), flight);
  }


  public Flight getFlight(String flightNumber) {
    return flights.get(flightNumber);
  }
}
