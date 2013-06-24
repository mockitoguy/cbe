import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tomasz.klaudel Date: 6/24/13 Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlightManager {

  private Map<String, Flight> flights;

  public FlightManager() {
    this.flights = new HashMap<String, Flight>();
  }

  public void addFlight(String id, int i) {
    Flight flight = new Flight(i);
    flights.put(id, flight);
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
    if (!flights.containsKey(id))
      throw new IncorrectFlightNumberException("Flight doesn't exist");
    return flights.get(id);
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

  public BigDecimal getAveragePrice(String id) {
    Flight flight = getFlight(id);
    List<Seat> seats = flight.getSeats();
    int count = 0;
    BigDecimal sum = new BigDecimal(0);
    for (Seat seat : seats) {
      if (seat.getUser() == null) {
        sum = sum.add(seat.getPrice());
        count++;
      }
    }

    return sum.divide(new BigDecimal(count));
  }

  public void addFlight(Flight flight) {
    flights.put(flight.getFlightNo(), flight);
  }

  public List<Flight> findFlightsFromToOn(String from, String to, Date on) {
    List<Flight> searchResult = new ArrayList<Flight>();
    for (Flight flight : flights.values()) {
      if (flight.getFrom().equals(from) && flight.getTo().equals(to) && flight.getOn().equals(on))
        searchResult.add(flight);
    }

    return searchResult;
  }

  public List<Flight> findFlightsTo(String to) {
    List<Flight> searchResult = new ArrayList<Flight>();
    for (Flight flight : flights.values()) {
      if (flight.getTo().equals(to))
        searchResult.add(flight);
    }

    return searchResult;

  }

  public List<Flight> findFlightsFrom(String from) {
    List<Flight> searchResult = new ArrayList<Flight>();
    for (Flight flight : flights.values()) {
      if (flight.getFrom().equals(from))
        searchResult.add(flight);
    }

    return searchResult;
  }

  public BigDecimal averagePriceInClass(String id, SeatClass c) {
    Flight flight = getFlight(id);
    int count = 0;
    BigDecimal sum = BigDecimal.ZERO;
    for (Seat seat : flight.getSeats()) {
      if(seat.getSeatClass().equals(c)){
        count++;
        sum = sum.add(seat.getPrice());
      }
    }

    return sum.divide(new BigDecimal(count));  //To change body of created methods use File | Settings | File Templates.
  }
}
