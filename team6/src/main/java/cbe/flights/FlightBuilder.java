package cbe.flights;

import java.util.Date;

public class FlightBuilder {
    private String flightName;
    private int availableSeats = 0;
    private Places from;
    private Places to;
    private Date date;

    public FlightBuilder setFlightName(String flightName) {
        this.flightName = flightName;
        return this;
    }

    public FlightBuilder setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
        return this;
    }

    public FlightBuilder setFrom(Places from) {
        this.from = from;
        return this;
    }

    public FlightBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public FlightBuilder setTo(Places to) {
        this.to = to;
        return this;
    }

    public Flight build() {
        return new Flight(flightName, availableSeats, date, from, to);
    }
}