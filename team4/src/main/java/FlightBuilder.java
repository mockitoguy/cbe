import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightBuilder {
  private String flightNo;
  private String from;
  private Date on;
  private List<Seat> seats;
  private int seatsCount;
  private String to;

  public FlightBuilder flightNo(String flightNo) {
    this.flightNo = flightNo;
    return this;
  }

  public FlightBuilder from(String from) {
    this.from = from;
    return this;
  }

  public FlightBuilder on(Date on) {
    this.on = on;
    return this;
  }

  public FlightBuilder seats(List<Seat> seats) {
    this.seats = seats;
    return this;
  }

  public FlightBuilder seatsCount(int seatsCount) {
    this.seatsCount = seatsCount;
    return this;
  }

  public FlightBuilder to(String to) {
    this.to = to;
    return this;
  }

  public Flight build() {
    return new Flight(flightNo, from, on, seats, seatsCount, to);
  }

  public FlightBuilder addSeat(Seat seat) {
    if(this.seats == null)
      this.seats = new ArrayList<Seat>();
    this.seats.add(seat);
    return this;
  }
}