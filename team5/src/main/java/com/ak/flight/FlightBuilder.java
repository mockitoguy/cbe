package com.ak.flight;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class FlightBuilder {

  private String flightNumber;
  private Set<Seat> seats = Sets.newHashSet();

  public FlightBuilder withFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
    return this;
  }

  public FlightBuilder addSeat(String seatNumber, long priceInCents) {
    seats.add(new Seat(seatNumber, priceInCents));
    return this;
  }

  public Flight build() {
    return new Flight(flightNumber, seats);
  }
}
