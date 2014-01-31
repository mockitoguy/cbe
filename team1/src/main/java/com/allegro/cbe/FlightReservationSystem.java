package com.allegro.cbe;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class FlightReservationSystem {

    private Multimap<Flight, Seat> flights = ArrayListMultimap.create();
    private Map<String, Flight> flightNumbers = new HashMap<>();

    public int checkAvailableSeats(String flightNumber) {
        checkArgument(getFlight(flightNumber) != null);

        Flight flight = getFlight(flightNumber);
        List<Seat> seats = (List<Seat>) flights.get(flight);
        int availableSeats = FluentIterable.from(seats).filter(new AvailableSeatPredicate()).size();

        return availableSeats;
    }

    private Flight getFlight(final String flightNumber) {
        return flightNumbers.get(flightNumber);
    }

    public void addFlight(Flight flight, Seat... flightSeats) {
        checkNotNull(flight != null);

        flightNumbers.put(flight.getFlightNumber(), flight);
        flights.putAll(flight, Lists.newArrayList(flightSeats));
    }

    public float findCheapestSeatPrice(String flightNumber) {
        List<Seat> seats = (List<Seat>) flights.get(getFlight(flightNumber));
        Collections.sort(seats, new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return Float.valueOf(o1.getPrice()).compareTo(o2.getPrice());
            }
        });

        return Iterables.getFirst(seats, null).getPrice();
    }

    public void bookSeat(String flightNumber, final String seatNumber) {
        checkArgument(getFlight(flightNumber) != null);

        Collection<Seat> seats = flights.get(getFlight(flightNumber));
        Optional<Seat> s = FluentIterable.from(seats).firstMatch(new Predicate<Seat>() {
            @Override
            public boolean apply(Seat seat) {
                return seat.getNumber().equals(seatNumber);
            }
        });

        assert s.isPresent();

        s.get().book();
    }

    public float findAveragePriceOfAvailableSeats(String flightNumber) {
        checkArgument(getFlight(flightNumber) != null);

        List<Seat> availableSeats =
                FluentIterable
                        .from(flights.get(getFlight(flightNumber)))
                        .filter(new AvailableSeatPredicate()).toList();

        float sumOfPrices = 0.0f;

        for(final Seat s : availableSeats) {
            sumOfPrices += s.getPrice();
        }

        return sumOfPrices / availableSeats.size();
    }

    public List<FlightInfo> findFlights(final String origin, final String destination) {
        checkArgument(origin != null);
        checkArgument(destination != null);

        return FluentIterable.from(flightNumbers.values())
                .filter(new RoutePredicate(origin, destination))
                .transform(new Function<Flight, FlightInfo>() {
                    @Override
                    public FlightInfo apply(final Flight flight) {
                        return new FlightInfo(flight.getFlightNumber(), flight.getDate());
                    }
                })
                .toList();
    }
}
