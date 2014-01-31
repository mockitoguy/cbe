package com.allegro.cbe;

import com.google.common.base.Predicate;

public class AvailableSeatPredicate implements Predicate<Seat> {
    @Override
    public boolean apply(Seat seat) {
        return seat.isAvailable();
    }
}
