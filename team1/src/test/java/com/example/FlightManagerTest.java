package com.example;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightManagerTest {

    private static final String FIRST_FLIGHT_NUMBER = "LH101";
    private static final Integer AVAILABLE_SEAT_NUMBER = 1;
    private static final String NON_EXISTING_FLIGHT_NUMBER = "nieistniejacy";
    private static final String SECOND_FLIGHT_NUMBER = "AS101";
    private static final Integer BOOKED_SEAT_NUMBER = 2;

    private FlightManager flightManager = new FlightManager();

    @Before
    public void setUp() {
        // flight 1
        Seat seat11 = new Seat(1, new BigDecimal("20.99"), true);
        Seat seat12 = new Seat(2, new BigDecimal("19.99"), true);
        Seat seat13 = new Seat(3, new BigDecimal("30.99"), false);

        Flight flight1 = new Flight(FIRST_FLIGHT_NUMBER);
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

        Flight flight2 = new Flight(SECOND_FLIGHT_NUMBER);
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
        int seats = flightManager.getAvailableSeats(FIRST_FLIGHT_NUMBER);

        // then
        assertEquals(2, seats);
    }

    @Test(expected = FlightManagerException.class)
    public void shouldThrowExceptionForUnknownFlightWhenAskingForAvailableSeats() throws FlightManagerException {
        // given

        // when
        flightManager.getAvailableSeats(NON_EXISTING_FLIGHT_NUMBER);
    }

    @Test
    public void shouldKnowPriceOfTheCheapestSeat() throws FlightManagerException {
        //given

        //when
        BigDecimal price = flightManager.getLowestSeatPrice(FIRST_FLIGHT_NUMBER);

        //then
        assertEquals(new BigDecimal("19.99"), price);
    }

    @Test(expected = FlightManagerException.class)
    public void shouldThrowExceptionWhenAskingForSeatLowestPriceForUnknownFlight() throws FlightManagerException {
        //given

        //when
        flightManager.getLowestSeatPrice(NON_EXISTING_FLIGHT_NUMBER);
    }

    @Test
    public void shouldBookSeatOnFlightWithAvailableSeats() {
        //given
        Seat seat = new SeatBuilder().withNumber(AVAILABLE_SEAT_NUMBER).available().build();
        Flight flight = new FlightBuilder().withNumber(FIRST_FLIGHT_NUMBER).withSeat(seat).build();
        flightManager.addFlight(flight);

        //when
        BookingStatus status = flightManager.bookSeat(FIRST_FLIGHT_NUMBER, AVAILABLE_SEAT_NUMBER);

        //then
        assertEquals(BookingStatus.SUCCESS, status);
    }

    @Test
    public void shouldFailWhenBookingUnavailableSeat() {
        //given
        Seat seat = new SeatBuilder().withNumber(BOOKED_SEAT_NUMBER).booked().build();
        Flight flight = new FlightBuilder().withNumber(FIRST_FLIGHT_NUMBER).withSeat(seat).build();
        flightManager.addFlight(flight);

        //when
        BookingStatus status = flightManager.bookSeat(FIRST_FLIGHT_NUMBER, BOOKED_SEAT_NUMBER);

        //then
        assertEquals(BookingStatus.FAIL, status);
    }

    @Test
    public void shouldKnowAveragePriceOfAvailableSeatsOnFlight(){
        //given
        Seat seat1 = new SeatBuilder().available().withPrice("10.00").build();
        Seat seat2 = new SeatBuilder().available().withPrice("20.00").build();
        Seat seat3 = new SeatBuilder().booked().withPrice("30.00").build();
        Flight flight = new FlightBuilder().withNumber(FIRST_FLIGHT_NUMBER).withSeats(seat1, seat2, seat3).build();
        flightManager.addFlight(flight);

        //when
        BigDecimal averagePrice = flightManager.getAveragePriceOfAvailableSeats(FIRST_FLIGHT_NUMBER);

        //then
        System.out.println(averagePrice);
        assertTrue(new BigDecimal("15.00").equals(averagePrice));
    }

    @Test
    public void shouldFindFlightsBetweenGivenLocations() {
        // given
        Flight flight1 = new FlightBuilder().withNumber(FIRST_FLIGHT_NUMBER).from("NY").to("WA").withDate(new Date()).build();
        flightManager.addFlight(flight1);

        Flight flight2 = new FlightBuilder().withNumber(SECOND_FLIGHT_NUMBER).from("WA").to("LA").withDate(new Date()).build();
        flightManager.addFlight(flight2);

        // when
        List<Flight> flights = flightManager.findFlights("NY", "WA");

        // then
        assertEquals(1, flights.size());
        assertEquals(flight1, flights.get(0));
    }

}
