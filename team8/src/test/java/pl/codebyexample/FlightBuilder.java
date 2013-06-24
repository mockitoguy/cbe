package pl.codebyexample;

import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: andrzej.wislowski Date: 24.06.2013 Time:
 * 15:43 To change this template use File | Settings | File Templates.
 */
public class FlightBuilder {
  private String number;
  private Date date;
  private String origin;
  private String destination;

  public FlightBuilder withNumber(String number) {
    this.number = number;
    return this;
  }

  public FlightBuilder withDate(Date date) {
    this.date = date;
    return this;
  }

  public FlightBuilder withOrigin(String origin) {
    this.origin = origin;
    return this;
  }

  public FlightBuilder withDestination(String destination) {
    this.destination = destination;
    return this;
  }

    public Flight build() {
        Flight flight = new Flight(number);
        flight.setDate(date);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        return flight;
    }
}
