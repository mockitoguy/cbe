package com.airway;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class FlightManager {
    private Map<String, Flight> flights = new HashMap<String, Flight>();

    public int getAvailableSeatsCount(String flightNumber) throws NoFlightFoundException {
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber).getAvailableSeats().size();
        } else {
            throw new NoFlightFoundException();
        }
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNo(), flight);
    }

    public Seat getCheapestSeat(String flightNo) throws NoFlightFoundException, NoSeatFoundException {
        if (!flights.containsKey(flightNo)) {
            throw new NoFlightFoundException();
        }

        Seat cheapestSeatPrice = null;
        for (Seat seat : flights.get(flightNo).getAvailableSeats().values()) {
            if (cheapestSeatPrice == null || cheapestSeatPrice.getPrice() > seat.getPrice()) {
                cheapestSeatPrice = seat;
            }
        }
        if (cheapestSeatPrice == null) {
            throw new NoSeatFoundException();
        }

        return cheapestSeatPrice;
    }

    public Seat doBookingSeat(String flightNo, String seatId) throws NoSeatAvailableException {
        return flights.get(flightNo).bookSeat(seatId);
    }

    public List<Flight> getFlightsBetween(final String origin, final String destination) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(@Nullable Flight flight) {
                return flight.getOrigin().equals(origin) && flight.getDestination().equals(destination);
            }
        }));
    }

    public List<Flight> getFlightsFromOrigin(final String origin) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(@Nullable Flight flight) {
                return flight.getOrigin().equals(origin);
            }
        }));
    }

    public List<Flight> getFlightsToDestination(final String destination) {
        return Lists.newArrayList(Collections2.filter(flights.values(), new Predicate<Flight>() {
            @Override
            public boolean apply(@Nullable Flight flight) {
                return flight.getDestination().equals(destination);
            }
        }));
    }
}
