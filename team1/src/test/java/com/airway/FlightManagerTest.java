package com.airway;

import org.junit.Test;

import java.util.List;

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

    @Test
    public void shouldThrowExceptionIfBookingFailed() throws NoSeatAvailableException {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder().setName("LH101").build();
        flightManager.addFlight(flightByLh);

        // when
        catchException(flightManager).doBookingSeat("LH101", "124A");

        // then
        assertThat(caughtException()).isInstanceOf(NoSeatAvailableException.class);
    }

    @Test
    public void shouldDecreaseNumberOfAvailableSeatsAfterBooking() {
        // TODO
    }

    @Test
    public void shouldTellAvailabaleSeatsCount() throws NoFlightFoundException {
        // given
        Seat seatA = new Seat("123a", 50);
        Seat seatB = new Seat("123b", 50);
        Seat seatC = new Seat("123c", 50);
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
        Seat businessSeat = new Seat("123a", 100);
        Seat ecoSeat = new Seat("124b", 50);
        Flight flight = Flight.builder().setName("LH101").addSeat(businessSeat).addSeat(ecoSeat).build();
        flightManager.addFlight(flight);

        // when
        Seat cheapestSeat = flightManager.getCheapestSeat("LH101");

        // then
        assertEquals(50, cheapestSeat.getPrice());
    }

    @Test
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

    @Test
    public void shouldReturnAListOfFlightsBetweenOriginAndDestination() {
        // given
        FlightManager flightManager = new FlightManager();
        Flight expectedFlight = Flight.builder()
                .setName("LH101")
                .setOrigin("Warszawa")
                .setDestination("New York")
                .setDeparture("2013-04-25T12:00:00")
                .setArrival("2013-04-25T12:30:00")
                .build();
        Flight flight = Flight.builder().setName("LH102").setOrigin("Warszawa").setDestination("Lodz").build();
        flightManager.addFlight(expectedFlight);
        flightManager.addFlight(flight);

        // when
        List<Flight> myFlights = flightManager.getFlightsBetween("Warszawa", "New York");

        // then
        assertThat(myFlights).containsOnly(expectedFlight);
    }

    @Test
    public void shouldReturnEmptyListWhenNoFlightsBetweenOriginAndDestination() {
        // given
        FlightManager flightManager = new FlightManager();

        // when
        List<Flight> myFlights = flightManager.getFlightsBetween("Warszawa", "New York");

        // then
        assertThat(myFlights).isEmpty();
    }

    @Test
    public void shouldReturnListOfFlightsFromGivenOrigin() {
        // given
        FlightManager flightManager = new FlightManager();
        Flight expectedFlight = Flight.builder()
                .setName("LH101")
                .setOrigin("Berlin")
                .setDestination("New York")
                .setDeparture("2013-04-25T12:00:00")
                .setArrival("2013-04-25T12:30:00")
                .build();
        Flight flight = Flight.builder().setName("LH102").setOrigin("Warszawa").setDestination("Lodz").build();
        flightManager.addFlight(expectedFlight);
        flightManager.addFlight(flight);

        // when
        List<Flight> myFlights = flightManager.getFlightsFromOrigin("Berlin");

        // then
        assertThat(myFlights).containsOnly(expectedFlight);
    }

    @Test
    public void shouldReturnListOfFlightsToGivenDestination() {
        // given
        FlightManager flightManager = new FlightManager();
        Flight expectedFlight = Flight.builder()
                .setName("LH101")
                .setOrigin("Warszawa")
                .setDestination("New York")
                .setDeparture("2013-04-25T12:00:00")
                .setArrival("2013-04-25T12:30:00")
                .build();
        flightManager.addFlight(expectedFlight);
        flightManager.addFlight(Flight.builder().setName("LH102").setOrigin("Warszawa").setDestination("Lodz").build());

        // when
        List<Flight> myFlights = flightManager.getFlightsToDestination("New York");

        // then
        assertThat(myFlights).containsOnly(expectedFlight);
    }

//    @Test
//    public void shouldReturnAveragePriceOfSeatsInAGivenFlight() {
//        // given
//        FlightManager flightManager = new FlightManager();
//        Flight firstFlight = Flight.builder()
//                .setName("LH101")
//                .addSeat(new Seat("123A", 100))
//                .addSeat(new Seat("321B", 250))
//                .build();
//        Flight secondFlight = Flight.builder()
//                .setName("LH101")
//                .addSeat(new Seat("123A", 100))
//                .addSeat(new Seat("321B", 250))
//                .build();
//        flightManager.addFlight(firstFlight);
//        flightManager.addFlight(secondFlight);
//        // when
//        flightManager.getAveragePriceOfSeats("LH101");
//
//        // then
//    }
}
