package com.example;

import java.util.Date;

public class FlightBuilder {

    private Flight flight = new Flight();

    public FlightBuilder withNumber(String flightNumber) {
        flight.setFlightNumber(flightNumber);
        return this;
    }

    public Flight build() {
        return flight;
    }

    public FlightBuilder withSeat(Seat seat) {
        flight.addSeat(seat);
        return this;
    }

    public FlightBuilder withSeats(Seat... seats) {
        for (Seat seat : seats) {
            flight.addSeat(seat);
        }
        return this;
    }

    public FlightBuilder from(String from) {
        flight.setFrom(from);
        return this;
    }

    public FlightBuilder to(String to) {
        flight.setTo(to);
        return this;
    }

    public FlightBuilder withDate(Date date) {
        flight.setDate(date);
        return this;
    }
}
