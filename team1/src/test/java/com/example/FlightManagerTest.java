package com.example;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    private FlightManager flightManager;

    @Before
    public void setUp() {
        flightManager = new FlightManager();

        // flight 1
        Seat seat11 = new Seat(1, new BigDecimal("20.99"), true);
        Seat seat12 = new Seat(2, new BigDecimal("19.99"), true);
        Seat seat13 = new Seat(3, new BigDecimal("30.99"), false);

        Flight flight1 = new Flight("LH101");
        flight1.addSeat(seat11);
        flight1.addSeat(seat12);
        flight1.addSeat(seat13);

        flightManager.addFlight(flight1);

        // flight 2
        Seat seat21 = new Seat(1, new BigDecimal("20.99"), true);
        Seat seat22 = new Seat(2, new BigDecimal("19.99"), true);
        Seat seat23 = new Seat(3, new BigDecimal("30.99"), true);
        Seat seat24 = new Seat(4, new BigDecimal("25.99"), false);
        Seat seat25 = new Seat(5, new BigDecimal("32.99"), false);

        Flight flight2 = new Flight("AS101");
        flight2.addSeat(seat21);
        flight2.addSeat(seat22);
        flight2.addSeat(seat23);
        flight2.addSeat(seat24);
        flight2.addSeat(seat25);

        flightManager.addFlight(flight2);
    }

    @Test
    public void shouldKnowNumberOfAvailableSeats() throws FlightManagerException {
        // given

        // when
        int seats = flightManager.getAvailableSeats("LH101");

        // then
        assertEquals(2, seats);
    }

    @Test(expected = FlightManagerException.class)
    public void shouldThrowExceptionForUnknownFlightWhenAskingForAvailableSeats() throws FlightManagerException {
        // given

        // when
        flightManager.getAvailableSeats("nieistniejacy");
    }

    @Test
    public void shouldKnowPriceOfTheCheapestSeat() throws FlightManagerException {
        //given

        //when
        BigDecimal price = flightManager.getLowestSeatPrice("LH101");

        //then
        assertEquals(new BigDecimal("19.99"), price);
    }

    @Test(expected = FlightManagerException.class)
    public void shouldThrowExceptionWhenAskingForSeatLowestPriceForUnknownFlight() throws FlightManagerException {
        //given

        //when
        flightManager.getLowestSeatPrice("nieistniejacy");
    }

}
