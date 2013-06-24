import java.util.HashMap;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(String flightName, int count) {
        flights.put(flightName, new Flight(count));
    }

    public int getAvailableSeatsCount(String name) {
        return flights.get(name).getSeatsCount();
    }

    public void addSeatPrice(String flightName, int seat, double price) {
        flights.get(flightName).setSeatPrice(seat, price);
    }

    public double getSeatPrice(String flightName, int seat) {
        return flights.get(flightName).getSeatPrice(seat);
    }

    public double getCheapestSeatPrice(String flightName) {

        Double cheapestPrice = Seat.UNSPECIFIED;
        for (Seat seat : flights.get(flightName).getSeats()) {

            Double seatPrice = seat.getPrice();
            if (cheapestPrice == Seat.UNSPECIFIED) {
                cheapestPrice = seatPrice;
            }

            if (seatPrice != Seat.UNSPECIFIED)
                cheapestPrice = Math.min(cheapestPrice, seatPrice);
        }
        return cheapestPrice;
    }
}
