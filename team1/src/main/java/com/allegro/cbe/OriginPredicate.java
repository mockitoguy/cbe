package com.allegro.cbe;

import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkArgument;

public class OriginPredicate implements Predicate<Flight>{

    private String origin;

    public OriginPredicate(String origin) {
        checkArgument(origin != null);

        this.origin = origin;
    }

    @Override
    public boolean apply(Flight flight) {
        return origin.equals(flight.getOrigin());
    }
}
