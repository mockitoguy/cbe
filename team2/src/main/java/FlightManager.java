import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public void addFlight(Flight flight) {
        //TODO: check not nulls
        if (flights.get(flight.getFlightNumber()) != null) {
            throw new FlightAlreadyExistsException("Flight already exists" + flight.getFlightNumber());
        }
        flights.put(flight.getFlightNumber(), flight);
    }


    public int getAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            throw new FlightNotFoundException();
        }
        return flight.getSeatsCapacity();
    }

    public void setPrice(String flightNumer, int seatNumber, int price) {
        Flight flight = flights.get(flightNumer);
        flight.seatAt(seatNumber).setValue(price);
    }

    public List<Seat> getCheapestSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        return flight.getCheapestSeats();
    }

    public void bookASeat(String flightNumber, int seatNumber) {
        seatAt(flightNumber, seatNumber).book();

    }

    Seat seatAt(String flightNumber, int seatNumber) {
        Flight flight = flights.get(flightNumber);
        return flight.seatAt(seatNumber);
    }

    public List<FlightDetails> getFlightsDetails(String origin, String destination) {
        List<FlightDetails> resultList = Lists.newArrayList();
        for(Flight flight : flights.values()){
         if(origin.equals(flight.getOrigin()) && destination.equals(flight.getDestination())){
            resultList.add(flight);
         }
       }

        return resultList;
    }
}
