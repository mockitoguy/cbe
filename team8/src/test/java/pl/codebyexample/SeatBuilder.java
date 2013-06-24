package pl.codebyexample;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA. User: andrzej.wislowski Date: 24.06.2013 Time:
 * 15:13 To change this template use File | Settings | File Templates.
 */
public class SeatBuilder {

  private String number = "1";
  private BigDecimal price = new BigDecimal(100);

  public Seat build() {
    return new Seat(number, price);
  }

  public SeatBuilder withPrice(int price) {
    this.price = new BigDecimal(price);
    return this;
  }

  public SeatBuilder withNumber(String number) {
    this.number = number;
    return this;
  }
}
