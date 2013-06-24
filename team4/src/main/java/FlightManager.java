import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: tomasz.klaudel Date: 6/24/13 Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlightManager {

  private Map<String, Flight> flightPropertiesMap;

  public FlightManager() {
    this.flightPropertiesMap = new HashMap<String, Flight>();
  }

  public void addFlight(String id, int i) {
    Flight flight = new Flight(i);
    flightPropertiesMap.put(id, flight);

  }

  public int getSeatsCountForFlight(String id) throws IncorrectFlightNumberException {
    return getFlight(id).getSeatsCount();
  }

  public BigDecimal getCheapestPlacePrice(String id) throws IncorrectFlightNumberException {
    Flight flight = getFlight(id);

    List<Seat> seatsPrices = flight.getSeats();
    int size = seatsPrices.size();
    BigDecimal cheapest = Flight.DEFAULT_PRICE;
    for (int i = 0; i < size; i++) {
      BigDecimal bigDecimal = seatsPrices.get(i).getPrice();
      cheapest = bigDecimal.compareTo(cheapest) < 0 ? bigDecimal : cheapest;
    }
    return cheapest;
  }

  public void setSeatPriceForFlight(String id, int i, BigDecimal v) throws IncorrectFlightNumberException {
    Flight flight = getFlight(id);

    Seat seat = flight.getSeats().get(i);
    seat.setPrice(v);
  }

  private Flight getFlight(String id) throws IncorrectFlightNumberException {
    if (!flightPropertiesMap.containsKey(id))
      throw new IncorrectFlightNumberException("Flight doesn't exist");
    return flightPropertiesMap.get(id);
  }

  public BigDecimal getSeatPriceForFlight(String id, int i) throws IncorrectFlightNumberException {
    Flight flight = getFlight(id);
    return flight.getSeats().get(i).getPrice();
  }

  public void bookPlace(String id, int i, User user) throws IncorrectFlightNumberException, SeatAlreadyBookedException {
    Flight flight = getFlight(id);
    Seat seat = flight.getSeats().get(i);
    if (seat.getUser() != null)
      throw new SeatAlreadyBookedException("Seat no " + i + " is already booked!");

    seat.setUser(user);
  }

  public User getSeatReservation(String id, int i) throws IncorrectFlightNumberException {
    Flight flight = getFlight(id);
    return flight.getSeats().get(i).getUser();
  }

}
