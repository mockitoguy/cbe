import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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

    public List<Flight> getFlightsWithGivenCondition(SearchCondition searchCondition) {
        return Lists.newLinkedList(Iterables.filter(flightList, searchCondition.predicate()));

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
