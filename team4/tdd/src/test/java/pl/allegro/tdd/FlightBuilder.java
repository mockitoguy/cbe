package pl.allegro.tdd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class FlightBuilder {
  private String destination;
  private String origin;
  private Set<Seat> seats = new HashSet<>();
  
  public FlightBuilder withDestination(String destination) {
    this.destination = destination;
    
    return this;
  }
  
  public FlightBuilder withOrigin(String origin) {
    this.origin = origin;
    
    return this;
  }
  
  public FlightBuilder withOriginAndDestination(String origin, String destination) {
    this.origin = origin;
    this.destination = destination;
    
    return this;
  }
  
  public FlightBuilder withSeats(Seat... seats) {
    this.seats.addAll(Arrays.asList(seats));
        
    return this;
  }
  
  public Flight build(String flightNumber) {
    Flight flight = new Flight(flightNumber);
    flight.setDestination(destination);
    flight.setOrigin(origin);
    flight.setSeats(seats);
    
    return flight;
  }
}
