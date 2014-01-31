package com.allegro.cbe;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FlightReservationSystemTest {

    @Test
    public void shouldCheckAvailableSeatsCount() throws Throwable {
        //given
        String flightNumber = "ABC123";
        Seat seat1 = new Seat("1", 1);
        Seat seat2 = new Seat("2", 1);

        FlightReservationSystem flightReservationSystem = new FlightReservationSystem();
        flightReservationSystem.addFlight(flightNumber, seat1, seat2);

        //when
        int availableSeats = flightReservationSystem.checkAvailableSeats(flightNumber);

        //then
        assertThat(availableSeats).isEqualTo(2);
    }

    @Test
    public void shouldFindCheapestSeatPerFlight() throws Throwable {
        //given
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

        String flightNumber = "LOT-123";

        Seat cheapestSeat = new Seat("1", 10.12f);
        Seat anotherSeat = new Seat("2", 15.00f);

        flightReservationSystem.addFlight(flightNumber, cheapestSeat, anotherSeat);

        //when
        float price = flightReservationSystem.findCheapestSeatPrice(flightNumber);

        //then
        assertThat(price).isEqualTo(10.12f);
    }

    @Test
    public void shouldBookSeat() throws Throwable {
        //given
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

        String flightNumber = "LOT-123";

        Seat seat1 = new Seat("1", 10.12f);
        Seat seat2 = new Seat("2", 15.00f);

        flightReservationSystem.addFlight(flightNumber, seat1, seat2);

        //when
        flightReservationSystem.bookSeat(flightNumber, "1");

        //then
        assertThat(flightReservationSystem.checkAvailableSeats(flightNumber)).isEqualTo(1);
    }

    @Test
    public void shouldFindAveragePriceOfAvailableSeats() throws Throwable {
        //given
        FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

        String flightNumber = "LOT-123";

        Seat availableSeat1 = new Seat("1", 1f);
        Seat availableSeat2 = new Seat("2", 3f);

        Seat bookedSeat1 = new Seat("3", 1000f);
        bookedSeat1.book();

        flightReservationSystem.addFlight(flightNumber, availableSeat1, availableSeat2, bookedSeat1);

        //when
        float averagePrice = flightReservationSystem.findAveragePriceOfAvailableSeats(flightNumber);

        //then
        assertThat(averagePrice).isEqualTo(2f);
    }
}
