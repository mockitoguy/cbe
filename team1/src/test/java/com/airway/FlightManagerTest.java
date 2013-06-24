package com.airway;

import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    @Test
    public void shouldBookSelectedSeatsOnGivenFlight() throws NoSeatAvailableException {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder()
                .setName("LH101")
                .addSeat(new Seat("123A", 100))
                .addSeat(new Seat("321B", 250))
                .build();
        flightManager.addFlight(flightByLh);

        // when
        Seat bookedSeat = flightManager.doBookingSeat("LH101", "123A");

        // then
        assertEquals("123A", bookedSeat.getSeatId());
    }

    public void shouldThrowExceptionIfBookingFailed() throws NoSeatAvailableException {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder().setName("LH101").build();
        flightManager.addFlight(flightByLh);

        // when
        catchException(flightManager.doBookingSeat("LH101", "124A"));

        // then
        assertThat(caughtException()).isInstanceOf(NoSeatAvailableException.class);
    }

    @Test
    public void shouldDecreaseNumberOfAvailableSeatsAfterBooking() {

    }

    @Test
    public void shouldTellAvailabaleSeatsCount() throws NoFlightFoundException {
        // given
        Seat seatA = new Seat("123a",50);
        Seat seatB = new Seat("123b",50);
        Seat seatC = new Seat("123c",50);
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder().setName("LH101").addSeat(seatA).addSeat(seatB).addSeat(seatC).build();
        flightManager.addFlight(flightByLh);

        Flight flightByRh = Flight.builder().setName("RH101").addSeat(seatA).addSeat(seatB).addSeat(seatC).build();
        flightManager.addFlight(flightByRh);

        // when
        int count = flightManager.getAvailableSeatsCount("LH101");

        // then
        assertEquals(3, count);
    }

    @Test
    public void shouldThrowExceptionWhenFlightDoesNotExistInGetAvailableSeatsCount() throws NoFlightFoundException {
        // given
        FlightManager flightManager = new FlightManager();

        // when
        catchException(flightManager).getAvailableSeatsCount("nope");

        // then
        assertThat(caughtException()).isInstanceOf(NoFlightFoundException.class);
    }

    @Test
    public void shouldGetACheapestSeat() throws NoSeatFoundException, NoFlightFoundException {
        // given
        FlightManager flightManager = new FlightManager();
        Seat businessSeat = new Seat("123a",100);
        Seat ecoSeat = new Seat("124b",50);
        Flight flight = Flight.builder().setName("LH101").addSeat(businessSeat).addSeat(ecoSeat).build();
        flightManager.addFlight(flight);

        // when
        Seat cheapestSeat = flightManager.getCheapestSeat("LH101");

        // then
        assertEquals(50, cheapestSeat.getPrice());
    }

    public void shouldThrowExceptionWhenNoSeats() throws NoSeatFoundException, NoFlightFoundException {
         // given
        FlightManager flightManager = new FlightManager();
        Flight flight = Flight.builder().setName("LH101").build();
        flightManager.addFlight(flight);

        // when
        catchException(flightManager).getCheapestSeat("LH101");

        // then
        assertThat(caughtException()).isInstanceOf(NoSeatFoundException.class);

    }

    @Test
    public void shouldThrowExceptionWhenFlightDoesNotExistInGetCheapestSeat() throws NoSeatFoundException, NoFlightFoundException {
        // given
        FlightManager flightManager = new FlightManager();

        // when
        catchException(flightManager).getCheapestSeat("nope");

        // then
        assertThat(caughtException()).isInstanceOf(NoFlightFoundException.class);
    }

}
