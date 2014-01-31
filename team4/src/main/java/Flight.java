import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class Flight {

    private String flightNo;
    private List<Seat> seats = new ArrayList<>();

    Flight() {
    }

    Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    Flight(String flightNo, List<Seat> seats) {
        this(flightNo);
        this.seats = new ArrayList<>(seats);
    }

    public int getAvailableSeatCount() {
        return seats.size();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public static class Builder {
        private String flightNo;
        private List<Seat> seats = new ArrayList<>();

        public Builder(String flightNo) {
            this.flightNo = flightNo;

        }

        public Builder withSeat(Seat seat) {
            seats.add(seat);
            return this;
        }

        public Flight build() {
            Flight flight = new Flight(flightNo, seats);

            return flight;
        }

    }
}
