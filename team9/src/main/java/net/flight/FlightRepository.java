package net.flight;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRepository {

  private Map<String, List<Seat>> flightSeats = new HashMap<String, List<Seat>>();

  public void addFlightSeats(String flightNo, Seat... seatPrices) {
    flightSeats.put(flightNo, asList(seatPrices));
  }

  public List<Seat> getFlightSeats(String flightNo) {
    return flightSeats.get(flightNo);
  }

}
