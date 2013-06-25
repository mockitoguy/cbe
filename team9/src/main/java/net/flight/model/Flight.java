package net.flight.model;

import java.util.List;

public class Flight {

  private final String flightNo;
  private String origin;
  private String destination;

  private List<Seat> seats;

  public Flight(String flightNo) {
    this.flightNo = flightNo;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getOrigin() {
    return origin;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getDestination() {
    return destination;
  }

}
