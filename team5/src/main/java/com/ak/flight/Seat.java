package com.ak.flight;

import com.ak.flight.exception.SeatAlreadyBookedException;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class Seat {

  private final String seatNumber;
  private final long priceInCents;
  private boolean booked;

  public Seat(String seatNumber, long priceInCents) {
    this.seatNumber = seatNumber;
    this.priceInCents = priceInCents;
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
}
