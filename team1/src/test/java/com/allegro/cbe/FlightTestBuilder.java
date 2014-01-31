package com.allegro.cbe;

/**
 * @author Rafal Glowinski
 * @since 31.01.14
 */
public class FlightTestBuilder {
    private String flightNumber;
    private String origin;
    private String destination;
    private String date;


    private FlightTestBuilder() {
        flightNumber = "";
        origin = "";
        destination = "";
        date = "";
    }

    public static FlightTestBuilder flight() {
        return new FlightTestBuilder();
    }

    public FlightTestBuilder withNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightTestBuilder withOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public FlightTestBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public FlightTestBuilder onDate(String date) {
        this.date = date;
        return this;
    }

    public Flight build() {
        return new Flight(flightNumber, origin, destination, date);
    }
}
