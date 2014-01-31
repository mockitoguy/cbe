package com.allegro.cbe;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Rafal Glowinski
 * @since 31.01.14
 */
public class Flight {

    private final String origin;
    private final String destination;
    private final String date;
    private final String flightNumber;

    public Flight(final String flightNumber, final String origin, final String destination, final String date) {
        checkArgument(flightNumber != null);
        checkArgument(origin != null);
        checkArgument(destination != null);
        checkArgument(date != null);

        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
