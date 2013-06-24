package pl.tdd.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * User: pcierpiatka
 */
public class FlightService {

    private Map<String, Flight> flightRepo = new HashMap<>();

    public void addFlight(Flight flight) {
        flightRepo.put(flight.getFlightCode(), flight);
    }

    public int getAvailabeSeatsCount(String flightCode) {
        return getFlight(flightCode).getSeatsCount();
    }

    public double getPriceOfCheapestFlightSeat(String flightCode) {
        Flight flight = getFlight(flightCode);
        return flight.getCheapestSeatPrice();
    }

    private Flight getFlight(String flightCode) {
        Flight flight = flightRepo.get(flightCode);
        if(flight == null) {
            throw new UnknownFlightException();
        }
        return flight;
    }

    public boolean isFlightSeatAvailable(String flightCode, String seatNumber) {
        Flight flight = getFlight(flightCode);
        Seat seat = flight.getSeat(seatNumber);
        return seat.isAvailable();
    }

    public void book(String flightCode, String seatNumber) {
        Flight flight = getFlight(flightCode);
        flight.bookSeat(seatNumber);
    }

    public double getAveragePriceOfAvailableSeats(String flightCode) {
        Flight flight = getFlight(flightCode);
        return flight.getAveragePriceOfAvailableSeats();
    }

    public List<Flight> findFlightsBetween(String origin, String destination) {
        List<Flight> listOfFlights = new LinkedList<>();
        for(Flight flight : flightRepo.values()) {
            if(flight.isFrom(origin) && flight.isTo(destination)) {
                listOfFlights.add(flight);
            }
        }
        return listOfFlights;
    }

    public List<Flight> findFlightsFrom(String origin) {
        List<Flight> listOfFlightsFrom = new LinkedList<>();
        for(Flight flight : flightRepo.values()) {
            if(flight.isFrom(origin)) {
                listOfFlightsFrom.add(flight);
            }
        }
        return listOfFlightsFrom;
    }

    public List<Flight> findFlightsTo(String destination) {
        List<Flight> listOfFlightsTo = new LinkedList<>();
        for(Flight flight : flightRepo.values()) {
            if(flight.isTo(destination)) {
                listOfFlightsTo.add(flight);
            }
        }
        return listOfFlightsTo;
    }

    public double getAveragePriceOfSeatsInClass(String flightCode, SeatClass seatClass) {
        Flight flight = getFlight(flightCode);
        return flight.getAveragePriceOfSeatsInClass(seatClass);
    }
}
