package tdd.builder;

import tdd.Flight;

public class FlightBuilder {

  private Flight flight;

  public FlightBuilder() {
    flight = new Flight();
  }

  public FlightBuilder withOrigin(String origin) {
    flight.setOrigin(origin);
    return this;
  }

  public FlightBuilder withDestination(String destination) {
    flight.setDestination(destination);
    return this;
  }
  
  public FlightBuilder withOriginAndDestination(String origin, String destination){
    withOrigin(origin);
    withDestination(destination);
    return this;
  }

  public Flight build() {
    return flight;
  }

}
