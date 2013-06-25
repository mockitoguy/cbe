package pl.allegro.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FlightManager {

  private Map<String, Flight> flights;

  public FlightManager() {
    flights = new HashMap<>();
  }

  public void addFlight(String flightNumber, Set<Seat> seats) {
    Flight flight = new Flight(flightNumber);
    flight.setSeats(seats);

    addFlight(flight);
  }

  public void addFlight(Flight flight) {
    flights.put(flight.getFlightNumber(), flight);
  }

  public Integer getAvailableSeats(String flightNumber) {
    return flights.get(flightNumber).getSeats().size();
  }

  public Seat getSeatWithCheapestPrice(String flightNumber) {
    Set<Seat> seats = flights.get(flightNumber).getSeats();

    Seat candidate = null;

    for (Seat seat : seats) {
      if (candidate == null || candidate.getPrice() > seat.getPrice()) {
        candidate = seat;
      }
    }

    return candidate;
  }

  public void addSeatToFlight(String flightNumber, Seat seat) {
    flights.get(flightNumber).addSeat(seat);
  }

  public void bookSeat(String flightNumber, int number) throws SeatAlreadyBookedException {
    if (flights.get(flightNumber).getSeat(number).isBooked()) {
      throw new SeatAlreadyBookedException("Seat already booked by someone else!");
    }
    
    flights.get(flightNumber).getSeat(number).setBooked(true);
  }

  public Flight getFlight(String flightNumber) {
    return flights.get(flightNumber);
  }

  public double getAveragePriceOfNonBookedSeats(String flightNumber) {
    Flight flight = flights.get(flightNumber);

    double sum = 0;
    int numerOfNonBookedSeat = 0;
    for (Seat seat : flight.getSeats()) {
      if (!seat.isBooked()) {
        sum += seat.getPrice();
        numerOfNonBookedSeat ++;
      }
    }

    if (numerOfNonBookedSeat == 0) {
      // TODO throw exception
    }
    
    return sum / numerOfNonBookedSeat;

  }

  public List<Flight> getFlightsBetween(String origin, String destination) {
    List<Flight> result = new ArrayList<>();
    
    for (Flight flight : flights.values()) {
      if (flight.getDestination().equals(origin) && flight.getDestination().equals(destination)) {
        result.add(flight);
      }
    }
    
    return result;
  }

  List<Flight> getFlightsFrom(String origin) {
    List<Flight> result = new ArrayList<>();
    
    for (Flight flight : flights.values()) {
      if (flight.getOrigin().equals(origin)) {
        result.add(flight);
      }
    }
    
    return result;
  }

  List<Flight> getFlightsTo(String destination) {
    List<Flight> result = new ArrayList<>();
    
    for (Flight flight : flights.values()) {
      if (flight.getDestination().equals(destination)) {
        result.add(flight);
      }
    }
    
    return result;
  }
}
