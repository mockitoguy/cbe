package tdd;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

public class Flight {

  private String number;
  private Set<Seat> seats = new HashSet<>();

  private String origin;
  private String destination;
  private Date startDate;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public void bookSeat(String seatNumber) {
    Assert.isTrue(hasSeat(seatNumber), "Cannot book nonexisting seat");
    getSeat(seatNumber).setBooked(true);
  }

  public boolean isSeatBooked(String seatNumber) {
    return hasSeat(seatNumber) ? getSeat(seatNumber).isBooked() : false;
  }

  public Set<Seat> getSeats() {
    return seats;
  }

  public void setSeats(Collection<Seat> seats) {
    this.seats = new HashSet<>(seats);
  }

  public int getSeatsCount() {
    return seats.size();
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Set<Seat> getNonBookedSeats() {
    Set<Seat> nonBookedSeats = new HashSet<>();
    for (Seat seat : seats) {
      if (!seat.isBooked()) {
        nonBookedSeats.add(seat);
      }
    }
    return nonBookedSeats;
  }

  public boolean hasSeat(String seatNumber) {
    Assert.notNull(seatNumber);
    return getSeat(seatNumber) != null;
  }

  private Seat getSeat(String seatNumber) {
    for (Seat seat : seats) {
      if (seat.getNumber().equals(seatNumber)) {
        return seat;
      }
    }
    return null;
  }
  
}
