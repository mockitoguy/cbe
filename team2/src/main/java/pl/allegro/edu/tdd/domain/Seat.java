package pl.allegro.edu.tdd.domain;

import java.math.BigDecimal;

public class Seat {

  private boolean available;

  private BigDecimal price;

  public Seat(BigDecimal price) {
    this.price = price;
  }

  public Seat(BigDecimal price, boolean available) {
    this(price);
    this.available = available;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
