import java.util.HashMap;
import java.util.Map;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public int getAvailableSeatsCount(String flightNumber) {
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber).getAvailableSeats().size();
        } else {
            throw new NoFlightFoundException();
        }
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNo(), flight);
    }

    public Seat getCheapestSeat(String flightNo) {
        if (!flights.containsKey(flightNo)) {
            throw new NoFlightFoundException();
        }

        Seat cheapestSeatPrice = null;
        for (Seat seat : flights.get(flightNo).getAvailableSeats().values()) {
            if (cheapestSeatPrice == null || cheapestSeatPrice.getPrice() > seat.getPrice()) {
                cheapestSeatPrice = seat;
            }
        }
        if (cheapestSeatPrice == null) {
            throw new NoSeatFoundException();
        }

        return cheapestSeatPrice;
    }

    public Seat doBookingSeat(String flightNo, String seatId) {

        return flights.get(flightNo).bookSeat(seatId);  //To change body of created methods use File | Settings | File Templates.
    }
}
