import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

    String number;
    ArrayList<Seat> seats = new ArrayList<Seat>();
    Date date;

    String origin;
    String destinantion;

    public Flight setFlightNo(String sampleFlightName) {
        number = sampleFlightName;
        return this;
    }

    public Flight addSeat(Seat seat) {
        seats.add(seat);
        return this;

    }

    public List<Seat> getAvaliableSeats() {
        ArrayList<Seat> availableSeats = new ArrayList<Seat>();

        for (Seat seat : seats) {
            if (!seat.isBooked) {
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    public Seat getCheapestSeats() {

        List<Seat> seats = getAvaliableSeats();

        Seat cheapestSeat = null;

        for (Seat seat : seats) {

            if (cheapestSeat == null || cheapestSeat.price > seat.price) {
                cheapestSeat = seat;
            }
        }

        if (cheapestSeat == null) {
            throw new IllegalStateException("Flight without seats");
        }

        return cheapestSeat;

    }

    public Seat getSeatOnFlight(int sampleSeatNumber) {

        List<Seat> availableSeats = getAvaliableSeats();

        for (Seat seat : availableSeats) {
            if (seat.number == sampleSeatNumber) {
                return seat;
            }
        }

        throw new IllegalArgumentException("No seat or already booked");
    }

    public double getAvaragePriceOfNonBookedSeats() {
        List<Seat> availableSeats = getAvaliableSeats();

        double sum = 0;

        for (Seat seat : availableSeats) {
            sum += seat.price;
        }

        return sum / availableSeats.size();
    }

    public Flight setDestination(String destination) {
        this.destinantion = destination;
        return this;
    }

    public Flight setOrigin(String origin) {
        this.origin = origin;
        return this;
    }
}
