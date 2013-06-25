package tdd;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

public class FlightManager {

  private Set<Flight> filghts = new HashSet<>();

  public void addFlight(Flight flight) {
    filghts.add(flight);
  }

  public int getAvailableSeats(String flightNumber) {
    Flight flight = getFlight(flightNumber);
    Assert.notNull(flight, "cannot get seats count for not existing flight");
    return flight.getSeatsCount();
  }

  private Flight getFlight(String number) {
    Assert.notNull(number);
    for (Flight flight : filghts) {
      if (flight.getNumber().equals(number)) {
        return flight;
      }
    }
    return null;
  }

}
