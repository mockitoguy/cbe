package net.flight;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRepository {

  private Map<String, Flight> flightMap = new HashMap<String, Flight>();

  public void addFlightSeats(String flightNo, Seat... seatPrices) {
    Flight flight = new Flight(flightNo);
    flight.setSeats(asList(seatPrices));

    flightMap.put(flightNo, flight);
  }

  public List<Seat> getFlightSeats(String flightNo) {
    return flightMap.get(flightNo).getSeats();
  }

  public void addFlights(Flight flight) {
    flightMap.put(flight.getFlightNo(), flight);
  }

  public Collection<Flight> getAllFlights() {
    return flightMap.values();
  }

}
