import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightManager {

    private HashMap<String, Flight> mFlightMap;

    public FlightManager() {
        mFlightMap = new HashMap<String, Flight>();
    }

    public void addFlight(String flightNumber, int seatsCount) {
        Flight flight = new Flight(flightNumber, seatsCount);
        mFlightMap.put(flightNumber, flight);
    }

    public FlightManager addFlight(Flight... flights) {
        for (Flight flight : flights) {
            mFlightMap.put(flight.getFlightNumber(), flight);
        }
        return this;
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

    public List<Flight> getFlightsBetween(String from, String to) {
        List<Flight> flightsList = new ArrayList<Flight>();

        for (String flightNumber : mFlightMap.keySet()) {
            Flight flight = mFlightMap.get(flightNumber);

            if (flight.getOrigin().equals(from)
                    && flight.getDestination().equals(to)) {
                flightsList.add(flight);
            }
        }
        return flightsList;
    }

    public List<Flight> getFlightsFrom(String from) {
        List<Flight> flightsList = new ArrayList<Flight>();

        for (String flightNumber : mFlightMap.keySet()) {
            Flight flight = mFlightMap.get(flightNumber);

            if (flight.getOrigin().equals(from)) {
                flightsList.add(flight);
            }
        }
        return flightsList;
    }

    public List<Flight> getFlightsTo(String to) {
        List<Flight> flightsList = new ArrayList<Flight>();

        for (String flightNumber : mFlightMap.keySet()) {
            Flight flight = mFlightMap.get(flightNumber);

            if (flight.getDestination().equals(to)) {
                flightsList.add(flight);
            }
        }
        return flightsList;
    }
}