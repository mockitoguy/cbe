import java.math.BigDecimal;

public class SeatBuilder {
  private BigDecimal price;
  private SeatClass seatClass;
  private User user;

  public SeatBuilder price(BigDecimal price) {
    this.price = price;
    return this;
  }

  public SeatBuilder seatClass(SeatClass seatClass) {
    this.seatClass = seatClass;
    return this;
  }

  public SeatBuilder user(User user) {
    this.user = user;
    return this;
  }

  public Seat build() {
    return new Seat(price, seatClass, user);
  }
}