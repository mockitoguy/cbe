package com.allegro.cbe;

import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.*;

public class RoutePredicate implements Predicate<Flight>{

    private String origin;
    private String destination;

    public RoutePredicate(String origin, String destination) {
        checkArgument(origin != null);
        checkArgument(destination != null);

        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public boolean apply(Flight flight) {
        return origin.equals(flight.getOrigin()) && destination.equals(flight.getDestination());
    }
}
