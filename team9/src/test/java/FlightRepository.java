import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRepository {

  private Map<String, List<Seat>> flightSeats = new HashMap<String, List<Seat>>();

  public void addFlightPrices(String flightNo, List<Seat> seatPrices) {
    flightSeats.put(flightNo, seatPrices);
  }

  public List<Seat> getFlightSeatsForFlight(String flightNo) {
    return flightSeats.get(flightNo);
  }

  public static class Seat {
    BigDecimal price;
    boolean booked = false;

    Seat(String price) {
      this.price = new BigDecimal(price);
    }
  }

}
