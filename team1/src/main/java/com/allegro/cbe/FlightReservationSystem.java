package com.allegro.cbe;


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
import java.util.List;

public class FlightReservationSystem {

    private Multimap<String, Seat> flights = ArrayListMultimap.create();

    public int checkAvailableSeats(String flightNumber) {
        assert flights.containsKey(flightNumber);

        List<Seat> seats = (List<Seat>) flights.get(flightNumber);
        int availableSeats = FluentIterable.from(seats).filter(new AvailableSeatPredicate()).size();

        return availableSeats;
    }

    public void addFlight(String flightNumber, Seat... flightSeats) {
        assert flightNumber != null;

        flights.putAll(flightNumber, Lists.newArrayList(flightSeats));
    }

    public float findCheapestSeatPrice(String flightNumber) {
        List<Seat> seats = (List<Seat>) flights.get(flightNumber);
        Collections.sort(seats, new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return Float.valueOf(o1.getPrice()).compareTo(o2.getPrice());
            }
        });

        return Iterables.getFirst(seats, null).getPrice();
    }

    public void bookSeat(String flightNumber, final String seatNumber) {
        assert flights.containsKey(flightNumber);

        Collection<Seat> seats = flights.get(flightNumber);
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
        assert flights.containsKey(flightNumber);

        List<Seat> availableSeats = FluentIterable.from(flights.get(flightNumber)).filter(new AvailableSeatPredicate()).toList();

        float sumOfPrices = 0.0f;

        for(final Seat s : availableSeats) {
            sumOfPrices += s.getPrice();
        }

        return sumOfPrices / availableSeats.size();
    }
}
