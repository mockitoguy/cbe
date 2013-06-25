package pl.allegro.tdd;

import java.util.HashSet;
import java.util.Set;

class Flight {
  private String destination;
  private String flightNumber;
  private String origin;
  private Set<Seat> seats = new HashSet<>();
  
  public Flight(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  void addSeat(Seat seat) {
    seats.add(seat);;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public Set<Seat> getSeats() {
    return seats;
  }

  public void setSeats(Set<Seat> seats) {
    this.seats = seats;
  }

  Seat getSeat(int number) {
    for (Seat seat : seats) {
      if (seat.getNumber() == number) {
        return seat;
      }
    }
    
    return null;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }
}
