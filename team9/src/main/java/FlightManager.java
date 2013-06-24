import java.util.HashMap;

public class FlightManager {

    private HashMap<String, FlightParameters> mFlightParams;

    public FlightManager() {
        mFlightParams = new HashMap<String, FlightParameters>();
    }

    public void addFlight(String flightNumber, int seatsCount) {
        FlightParameters flightParameters = new FlightParameters(seatsCount);
        mFlightParams.put(flightNumber, flightParameters);
    }

    public void addFlight(String flightNumber, FlightParameters flightParameters) {
        mFlightParams.put(flightNumber, flightParameters);
    }

    public int getAvailableSeatsCount(String flightNumber) throws FlightNotFoundException {
        return getFlightParametersOrThrow(flightNumber).getSeatsCount();
    }

    private FlightParameters getFlightParametersOrThrow(String flightNumber) throws FlightNotFoundException {
        if (!mFlightParams.containsKey(flightNumber)) {
            throw new FlightNotFoundException();
        }
        return mFlightParams.get(flightNumber);
    }

    public int getCheapestSeatPrice(String flightNumber) throws FlightException {
        FlightParameters flightParameters = getFlightParametersOrThrow(flightNumber);
        return flightParameters.getCheapestFreeSeat();
    }

    public boolean bookSelectedSeat(String flightNumber, int seatNumber) throws FlightException {
        FlightParameters flightParameters = getFlightParametersOrThrow(flightNumber);

        return flightParameters.reserveSeat(seatNumber);
    }

    public int getAvgSeatPrice(String flightNumber) throws FlightException {
        FlightParameters flightParameters = getFlightParametersOrThrow(flightNumber);

        return flightParameters.getAvgPrice();  //To change body of created methods use File | Settings | File
        // Templates.
    }
}
