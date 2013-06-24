import java.util.HashMap;

public class FlightManager {

    private HashMap<String, Flight> mFlightMap;

    public FlightManager() {
        mFlightMap = new HashMap<String, Flight>();
    }

    public void addFlight(String flightNumber, int seatsCount) {
        Flight flight = new Flight(seatsCount);
        mFlightMap.put(flightNumber, flight);
    }

    public void addFlight(String flightNumber, Flight flight) {
        mFlightMap.put(flightNumber, flight);
    }

    public int getAvailableSeatsCount(String flightNumber) throws FlightNotFoundException {
        return getFlightOrThrow(flightNumber).getSeatsCount();
    }

    private Flight getFlightOrThrow(String flightNumber) throws FlightNotFoundException {
        if (!mFlightMap.containsKey(flightNumber)) {
            throw new FlightNotFoundException();
        }
        return mFlightMap.get(flightNumber);
    }

    public int getCheapestSeatPrice(String flightNumber) throws FlightException {
        Flight flight = getFlightOrThrow(flightNumber);
        return flight.getCheapestFreeSeat();
    }

    public boolean bookSelectedSeat(String flightNumber, int seatNumber) throws FlightException {
        Flight flight = getFlightOrThrow(flightNumber);

        return flight.reserveSeat(seatNumber);
    }

    public int getAvgSeatPrice(String flightNumber) throws FlightException {
        Flight flight = getFlightOrThrow(flightNumber);

        return flight.getAvgPrice();
    }
}
