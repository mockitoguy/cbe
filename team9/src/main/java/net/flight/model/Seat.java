package net.flight.model;

import java.math.BigDecimal;

public class Seat {

  private final BigDecimal price;
  private boolean booked = false;

  public Seat(BigDecimal price, boolean booked) {
    this.price = price;
    this.booked = booked;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }
}
