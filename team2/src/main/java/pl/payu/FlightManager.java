package pl.payu;

import java.util.HashMap;
import java.util.Map;

import pl.payu.exceptions.FlightNotExistsException;

public class FlightManager {

    private Map<String, Flight> flights = new HashMap<String, Flight>();
    
    public void addFlight(Flight flight) {
        this.flights.put(flight.getFlightNo(), flight);
    }

    public int getAvailableSeats(String flightNo) throws FlightNotExistsException {
        return findFlight(flightNo).getAvagetAvailableSeats();
    }

    public double getCheapestPrice(String flightNo) throws FlightNotExistsException {
        return findFlight(flightNo).getCheapestSeatPrice();
    }

    public Flight findFlight(String flightNo) throws FlightNotExistsException {
        Flight flight = flights.get(flightNo);
        if (flight == null) {
            throw new FlightNotExistsException(String.format("Flight: %s not exists", flight));
        }
        return flight;
    }
    
}
