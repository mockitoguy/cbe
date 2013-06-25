import net.flight.model.Seat;

import java.math.BigDecimal;

public class SeatBuilder {

  private boolean booked = false;
  private BigDecimal price;

  public SeatBuilder available() {
    this.booked = false;
    return this;
  }

  public SeatBuilder booked() {
    this.booked = true;
    return this;
  }

  public Seat build() {
    return new Seat(price, booked);
  }

  public SeatBuilder withPrice(String price) {
    this.price = new BigDecimal(price);
    return this;
  }
}
