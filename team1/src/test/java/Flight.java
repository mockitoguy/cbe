import java.util.HashMap;
import java.util.Map;

public class Flight {

    private final String flightNo;
    private final Map<String, Seat> availableSeats;
    private final Map<String, Seat> bookedSeats;

    private Flight(String flightNo, Map<String, Seat> availableSeats) {
        this.flightNo = flightNo;
        this.availableSeats = availableSeats;
        this.bookedSeats = new HashMap<String, Seat>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Seat bookSeat(String seatId) {
        Seat bookedSeat = availableSeats.get(seatId);
        bookedSeats.put(seatId, bookedSeat);
        availableSeats.remove(seatId);
        return bookedSeat;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public static class Builder {

        private String flightNo;
        private Map<String, Seat> seats = new HashMap<String, Seat>();

        public Builder setName(String flightNo) {
            this.flightNo = flightNo;
            return this;
        }

        public Builder addSeats(Seat seat) {
            seats.put(seat.getSeatId(), seat);
            return this;
        }

        public Flight build() {
            return new Flight(flightNo, seats);
        }
    }

}
