package pl.codebyexample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created with IntelliJ IDEA. User: andrzej.wislowski Date: 24.06.2013 Time:
 * 10:56 To change this template use File | Settings | File Templates.
 */
public class Flight {

  String flightNumber;
  HashSet<Seat> seats = new LinkedHashSet<Seat>();
    private Date date;
    private String origin;
    private String destination;

    public Flight(String flightNumber) {// }, String origin, String destination) {
    this.flightNumber = flightNumber;
  }

  public int getAvailableSeats() {
    int availableSeats = 0;
    for (Seat seat : seats) {
      if (seat.isAvailable())
        availableSeats++;
    }
    return availableSeats;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public Flight addSeat(Seat seat) {
    seats.add(seat);
    return this;
  }

  public BigDecimal getCheapestSeat() {
    BigDecimal cheapest = new BigDecimal(Integer.MAX_VALUE);
    for (Seat seat : seats) {
      if (cheapest.compareTo(seat.getPrice()) > 0)
        cheapest = seat.getPrice();
    }
    return cheapest;
  }

  public boolean containsSeat(String seatNumber) {
    for (Seat seat : seats) {
      if (seat.getNumber().equals(seatNumber))
        return true;
    }
    return false;
  }

  public boolean isSeatAvailable(String seatNumber) {
    for (Seat seat : seats) {
      if (seat.getNumber().equals(seatNumber))
        return seat.isAvailable();
    }
    return false;
  }

  public String bookSeat() {
    for (Seat seat : seats) {
      if (seat.isAvailable()) {
        seat.setAvailable(false);
        return seat.getNumber();
      }
    }
    return null;
  }

  public BigDecimal getAvaragePriceOfAvailableSeats() {
    int count = 0;
    BigDecimal amount = BigDecimal.ZERO;
    for (Seat seat : seats) {
      if (seat.isAvailable()) {
        count++;
        amount = amount.add(seat.getPrice());
      }
    }
    if (count > 0) {
      return amount.divide(new BigDecimal(count));
    }

    return BigDecimal.ZERO;
  }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
