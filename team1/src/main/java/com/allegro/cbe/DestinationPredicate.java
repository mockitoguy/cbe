package com.allegro.cbe;

import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkArgument;

public class DestinationPredicate implements Predicate<Flight>{

    private String destination;

    public DestinationPredicate(String destination) {
        checkArgument(destination != null);

        this.destination = destination;
    }

    @Override
    public boolean apply(Flight flight) {
        return destination.equals(flight.getDestination());
    }
}
