package pl.payu;

import java.util.Date;

public class FlightBuilder {

    private Flight flight;
    private int seatNo=1;

    public FlightBuilder(String flightNo) {
        this.flight = new Flight(flightNo);
    }

    public FlightBuilder destination(String destination) {
        flight.setDestination(destination);
        return this;
    }
    
    public FlightBuilder origin(String origin) {
        flight.setOrigin(origin);
        return this;
    }

    public FlightBuilder date(Date date) {
        flight.setDate(date);
        return this;
    }
    
    public Flight build() {
        return flight;
    }

    public FlightBuilder seat(int seatNo, double price) {
        flight.addSeat(new Seat(seatNo, price));
        return this;
    }

    public FlightBuilder seat() {
        flight.addSeat(new Seat(seatNo++, 10.0));
        return this;
    }
}
