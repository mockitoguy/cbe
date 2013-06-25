import java.util.ArrayList;
import java.util.List;

public class FlightManager {

    List<Flight> flightList;

    FlightManager(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Seat> getAvaliableSeatsForFlight(String sampleFlightName) {
        for (Flight flight : flightList) {
            if (flight.number.equals(sampleFlightName)) {
                return flight.seat;
            }
        }
        throw new IllegalArgumentException("Flight number not found");
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
