import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Flight {


  public static final BigDecimal DEFAULT_PRICE = BigDecimal.ONE;
  private int seatsCount;
  private List<Seat> seats;

  public Flight(int seatsCount) {
    this.seatsCount = seatsCount;
    this.seats = new ArrayList<Seat>();
    for (int i = 0; i < seatsCount; i++) {
      seats.add(new Seat(DEFAULT_PRICE));
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
