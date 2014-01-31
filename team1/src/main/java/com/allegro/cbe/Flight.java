package com.allegro.cbe;

/**
 * @author Rafal Glowinski
 * @since 31.01.14
 */
public class Flight {

    private String flightNumber;

    public Flight(final String flightNumber, final String origin, final String destination, final String date) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}
