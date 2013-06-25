import java.util.HashMap;
import java.util.Map;

public class FlightBuilder {
    private String flightNo;

    private int availableSeats = 0;

    private Map<String, Seat> seats = new HashMap<String, Seat>();

    public FlightBuilder setFlightNo(String flightNo) {
        this.flightNo = flightNo;
        return this;
    }

    public Flight createFlight() {
        return new Flight(flightNo, availableSeats, seats);
    }

    public FlightBuilder addSeat(Seat seat) {
        if (!seat.isBooked()) this.availableSeats += 1;

        this.seats.put(seat.getSeatNo(), seat);
        return this;
    }
}