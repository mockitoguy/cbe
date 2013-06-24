import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

  public static final BigDecimal DEFAULT_PRICE = BigDecimal.ONE;
  private int seatsCount;
  private List<Seat> seats;
  private String flightNo;
  private String from;
  private String to;
  private Date on;

  public Flight(String flightNo, String from, Date on, List<Seat> seats, int seatsCount, String to) {
    this.flightNo = flightNo;
    this.from = from;
    this.on = on;
    this.seats = seats;
    this.seatsCount = seatsCount;
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public Date getOn() {
    return on;
  }

  public void setOn(Date on) {
    this.on = on;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public Flight() {
    this.seatsCount = 0;
    this.seats = new ArrayList<Seat>();
  }

  public Flight(List<Seat> seats, int seatsCount) {
    this.seats = seats;
    this.seatsCount = seatsCount;
  }

  public Flight(int seatsCount) {
    this.seatsCount = seatsCount;
    this.seats = new ArrayList<Seat>();
    for (int i = 0; i < seatsCount; i++) {
      seats.add(new SeatBuilder().price(DEFAULT_PRICE).build());
    }
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public int getSeatsCount() {
    return seatsCount;
  }

  public void setSeatsCount(int seatsCount) {
    this.seatsCount = seatsCount;
  }
}
