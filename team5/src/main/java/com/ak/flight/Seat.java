package com.ak.flight;

import com.ak.flight.exception.SeatAlreadyBookedException;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class Seat {

  private final String seatNumber;
  private long priceInCents;
  private FlightClass flightClass;
  private boolean booked;

  public Seat(String seatNumber, long priceInCents, FlightClass flightClass) {
    this.seatNumber = seatNumber;
    this.priceInCents = priceInCents;
    this.flightClass = flightClass;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public long getPriceInCents() {
    return priceInCents;
  }

  public void book() throws SeatAlreadyBookedException {
    if (booked) {
      throw new SeatAlreadyBookedException();
    }
    booked = true;
  }


  public boolean isBooked() {
    return booked;
  }

  public FlightClass getFlightClass() {
    return flightClass;
  }
}
