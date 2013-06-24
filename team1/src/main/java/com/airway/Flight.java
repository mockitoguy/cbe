package com.airway;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Flight {

    private final String flightNo;
    private DateTime departure;
    private DateTime arrive;
    private String origin;
    private String destination;
    private final Map<String, Seat> availableSeats;
    private final Map<String, Seat> bookedSeats;

    private Flight(String flightNo, String origin, String destination, Map<String, Seat> availableSeats) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.bookedSeats = new HashMap<String, Seat>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Seat bookSeat(String seatId) throws NoSeatAvailableException {
        Seat bookedSeat = availableSeats.get(seatId);
        if (bookedSeat == null) {
            throw new NoSeatAvailableException();
        }

        bookedSeats.put(seatId, bookedSeat);
        availableSeats.remove(seatId);
        return bookedSeat;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public DateTime getDeparture() {
        return departure;
    }

    public DateTime getArrive() {
        return arrive;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public static class Builder {

        private String flightNo;
        private Map<String, Seat> seats = new HashMap<String, Seat>();
        private DateTime departure;
        private DateTime arrival;
        private String origin;
        private String destination;

        public Builder setName(String flightNo) {
            this.flightNo = flightNo;
            return this;
        }

        public Builder addSeat(Seat seat) {
            seats.put(seat.getSeatId(), seat);
            return this;
        }

        public Builder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setDeparture(String departure) {
            this.departure = new DateTime(departure);
            return this;
        }

        public Builder setArrival(String arrival) {
            this.arrival = new DateTime(arrival);
            return this;
        }

        public Flight build() {
            return new Flight(flightNo, origin, destination, seats);
        }
    }

}
