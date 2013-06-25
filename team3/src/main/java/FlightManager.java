import java.util.ArrayList;
import java.util.List;

public class FlightManager {

    List<Flight> flightList;

    FlightManager(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Seat> getAvaliableSeatsForFlight(String sampleFlightName) {

        List<Seat> availableSeats = new ArrayList<>();

        List<Seat> allSeats = getFlightByName(sampleFlightName).seats;

        for (Seat seat : allSeats) {
            if (!seat.isBooked) {
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    public Seat getCheapestSeatsForFlight(String sampleFlightName) {

        List<Seat> seats = getAvaliableSeatsForFlight(sampleFlightName);

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

    public Seat bookSeatOnFlight(String sampleFlightName, int sampleSeatNumber) {

        List<Seat> availableSeats = getAvaliableSeatsForFlight(sampleFlightName);

        for (Seat seat : availableSeats) {
            if (seat.number == sampleSeatNumber) {
                return seat.book();
            }
        }

        throw new IllegalArgumentException("No seat or already booked");
    }

    private Flight getFlightByName(String flightName) throws IllegalArgumentException {
        for (Flight flight : flightList) {
            if (flight.number.equals(flightName)) {
                return flight;
            }
        }

        throw new IllegalArgumentException("Flight number not found");
    }

    public static class Builder {

        List<Flight> arrayList = new ArrayList<Flight>();

        public Builder addFlight(Flight flight) {

            arrayList.add(flight);
            return this;
        }

        public FlightManager build() {
            return new FlightManager(arrayList);
        }
    }

}
