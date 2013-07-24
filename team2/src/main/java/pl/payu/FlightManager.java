package pl.payu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pl.payu.exceptions.FlightNotExistsException;

public class FlightManager {

    private Map<String, Flight> flights = new LinkedHashMap<String, Flight>();
    
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

    public List<Flight> findFlights(String origin, String destination) {
        List<Flight> flightsResult = new ArrayList<Flight>();
        for (Flight flight : flights.values()) {
            if (origin.equals(flight.getOrigin()) && destination.equals(flight.getDestination())) {
                flightsResult.add(flight);
            }
        }
        return flightsResult;
    }

    public List<Flight> findFlightsByOrigin(String origin) {
        List<Flight> flightsResult = new ArrayList<Flight>();
        for (Flight flight : flights.values()) {
            if (origin.equals(flight.getOrigin())) {
                flightsResult.add(flight);
            }
        }
        return flightsResult;
    }

    public List<Flight> findFlightsByDestination(String destination) {
        List<Flight> flightsResult = new ArrayList<Flight>();
        for (Flight flight : flights.values()) {
            if (destination.equals(flight.getDestination())) {
                flightsResult.add(flight);
            }
        }
        return flightsResult;
    }

    public Seat findSeat(String flightNo, int seatNo) throws FlightNotExistsException {
        return findFlight(flightNo).findSeat(seatNo);
    }

    public Seat findNonBookedSeat(String flightNo) throws FlightNotExistsException {
        return findFlight(flightNo).findNonBookedSeat();
        
    }
    
}
