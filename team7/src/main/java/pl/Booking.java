package pl;

import java.util.HashMap;
import java.util.Map;

public class Booking {
  private Map<String, Seats> seatsNoPerFlight = new HashMap<String, Seats>();

  public void addFlight(String flight, Seats seats) {
    if (seats.count() < 0 || flight == null || flight.trim().length() == 0)
      throw new IllegalArgumentException("Not good flight");
    this.seatsNoPerFlight.put(flight, seats);
  }

  public int findFreeSeatsForFlight(String flight) {
    if (this.seatsNoPerFlight.containsKey(flight))
      return seatsNoPerFlight.get(flight).count();
    else return 0;
  }

  public int findCheapestForFlight(String flight) {
    return seatsNoPerFlight.get(flight).cheapestAvailableSeatPrice();
  }
}
