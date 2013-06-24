package pl.tdd.service;

import com.sun.javafx.scene.layout.region.BorderImage;

/**
 * User: pcierpiatka
 */
public class FlightTestBuilder {

    private Flight flight;

    private FlightTestBuilder(String flightCode) {
        flight = new Flight(flightCode);
    }

    public static FlightTestBuilder flight(String flightCode) {
        return new FlightTestBuilder(flightCode);
    }

    public Flight build() {
        return flight;
    }

    public FlightTestBuilder withSeats(int seatCount) {
        return withSeatsInPrice(seatCount, 0);
    }

    public FlightTestBuilder withSeatsInPrice(int seatsCount, double price) {
        int paddedSeatsCount = flight.getSeatsCount() + seatsCount;

        for (int seatIndex = flight.getSeatsCount(); seatIndex < paddedSeatsCount; ++seatIndex) {
            flight.addSeat("seat" + seatIndex, price);
        }
        return this;
    }

    public FlightTestBuilder withBookedSeatsInPrice(int seatsCount, double price) {
        int paddedSeatsCount = flight.getSeatsCount() + seatsCount;
        String seatNumber;

        for (int seatIndex = flight.getSeatsCount(); seatIndex < paddedSeatsCount; ++seatIndex) {
            seatNumber = "seat" + seatIndex;
            flight.addSeat(seatNumber, price);
            flight.bookSeat(seatNumber);
        }
        return this;
    }

    public FlightTestBuilder withSeat(String seatNumber) {
        flight.addSeat(seatNumber, 0);
        return this;
    }

    public FlightTestBuilder withBookeSeat(String seatNumber) {
        flight.addSeat(seatNumber, 0);
        flight.bookSeat(seatNumber);
        return this;
    }
}
