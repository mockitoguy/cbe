import java.util.HashMap;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(String flightName, int count, Double defaultPrice) {
        flights.put(flightName, new Flight(count, defaultPrice));
    }

    public int getAvailableSeatsCount(String name) {
        return getFlight(name).getSeatsCount();
    }

    public void addSeatPrice(String flightName, int seat, double price) {
        getFlight(flightName).setSeatPrice(seat, price);
    }

    public double getSeatPrice(String flightName, int seat) {
        return getFlight(flightName).getSeatPrice(seat);
    }

    public double getCheapestSeatPrice(String flightName) {

        return getFlight(flightName).getCheapestSeatPrice();

    }

    private Flight getFlight(String flightName) {
        return flights.get(flightName);
    }

    public void reserveSeatInFlight(int seat, String flightName) {

        Flight flight = getFlight(flightName);

        flight.reserveSeat(seat);


    }

    public boolean isSeatInFlightReserved(int seat, String flightName) {
        return getFlight(flightName).isSeatReserved(seat);
    }
}
