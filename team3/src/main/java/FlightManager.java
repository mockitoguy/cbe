import java.util.ArrayList;
import java.util.List;

public class FlightManager {

    List<Flight> flightList;

    FlightManager(List<Flight> flightList) {
        this.flightList = flightList;
    }

    Flight getFlightByName(String flightName) throws IllegalArgumentException {
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
