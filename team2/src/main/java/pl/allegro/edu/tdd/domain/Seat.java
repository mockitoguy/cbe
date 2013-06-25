package pl.allegro.edu.tdd.domain;

public class Seat {

  private boolean available;

  private long price;

  public Seat(long price) {
    this.price = price;
  }

  public Seat(long price, boolean available) {
    this(price);
    this.available = available;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
