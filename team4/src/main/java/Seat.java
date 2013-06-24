import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA. User: tomasz.klaudel Date: 6/24/13 Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Seat {

  private BigDecimal price;
  private User user = null;

  public Seat(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
