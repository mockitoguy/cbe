package com.ak.flight;

import com.ak.flight.exception.SeatAlreadyBookedException;

import java.util.UUID;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class SeatBuilder {

  private String seatNumber = UUID.randomUUID().toString();
  private boolean booked;
  private long priceInCents;

  public SeatBuilder booked() {
    this.booked = true;
    return this;
  }


  public Seat build() {
    Seat seat = new Seat(seatNumber, priceInCents);
    if (booked) {
      try {
        seat.book();
      } catch (SeatAlreadyBookedException ignored) {
      }
    }
    return seat;
  }

  public SeatBuilder number(String seatNumber) {
    this.seatNumber = seatNumber;
    return this;
  }

  public SeatBuilder price(int price) {
    this.priceInCents = price;
    return this;
  }
}
