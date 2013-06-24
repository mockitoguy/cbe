package cbe.flights;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

/**
 * @author tskrobol
 */
public class FlightManagerTest {

    public static final String FLIGHT_NAME = "TU128";
    public static final int AVAILABLE_SEATS = 2;

    @Test
    public void shouldTellAvailableSeatsCount() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight(
            new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(AVAILABLE_SEATS).build());
        manager.addFlight(new FlightBuilder().setFlightName("LO888").setAvailableSeats(44).build());

        //when
        int seatsCount = manager.getAvailableSeatsCount(FLIGHT_NAME);

        //then
        assertEquals(AVAILABLE_SEATS, seatsCount);
    }

    @Test
    public void shouldTellAvailableSeatsCountForUnknownFligt() {
        //given
        FlightManager manager = new FlightManager();

        //when
        int seatsCount = manager.getAvailableSeatsCount(FLIGHT_NAME);

        //then
        assertEquals(0, seatsCount);
    }

    @Test
    public void shouldGetCheapestSeatsInFlight() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(
            AVAILABLE_SEATS).build();
        flight.addSeat(new SeatBuilder().setPrice(1d).buildEconomySeat()) ;
        flight.addSeat(new SeatBuilder().setPrice(2d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(4d).buildEconomySeat());


        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        //when
        Seat cheapestSeat = manager.getCheapestSeat(FLIGHT_NAME);

        //then
        assertEquals(1d, cheapestSeat.getPrice(), 0.01);
    }

    @Test
    public void shouldBookSeat() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(
            AVAILABLE_SEATS).build();
        flight.addSeat(new SeatBuilder().setPrice(1d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(3d).buildEconomySeat());


        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        User user = new User("Tomek");

        //when
        boolean booked = manager.bookSeatInFlight(FLIGHT_NAME, 1, user);

        //then
        assertTrue(booked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBlockSetPriceWhenMaxSeatsReached() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(2).build();
        flight.setSeatPrice(0, 2d);
        flight.setSeatPrice(1, 3d);
        //when
        flight.setSeatPrice(2, 3d);
        //throw exception
    }


    @Test
    public void shouldFailSecondBookSeat() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(
            AVAILABLE_SEATS).build();
        flight.addSeat(new SeatBuilder().setPrice(2d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(3d).buildEconomySeat());

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        User user = new User("Tomek");

        //when
        manager.bookSeatInFlight(FLIGHT_NAME, 1, user);
        boolean booked = manager.bookSeatInFlight(FLIGHT_NAME, 1, user);

        //then
        assertFalse(booked);
    }

    @Test
    public void shouldGetNotBookedSeatAvaragePrice() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(3).build();
        flight.addSeat(new SeatBuilder().setPrice(2d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(2d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(4d).buildEconomySeat());

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        User user = new User("Tomek");
        boolean booked = manager.bookSeatInFlight(FLIGHT_NAME, 1, user);

        //when
        double avaragePrice = manager.getNonBookedAvaragePrice(FLIGHT_NAME);

        //then
        assertEquals(3d, avaragePrice, 0.01);
    }

    @Test
    public void shouldFindFromFlight() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setFrom(
            Places.WARSAW).setTo(Places.CHICAGO).build();
        Flight flight2 = new FlightBuilder().setFlightName("KIO876").setFrom(
            Places.WARSAW).setTo(Places.CHICAGO).build();
        Flight flight3 = new FlightBuilder().setFlightName("BIG820").setFrom(
            Places.NEW_YORK).setTo(Places.CHICAGO).build();

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);
        manager.addFlight(flight2);
        manager.addFlight(flight3);

        //when
        Set<Flight> flights = manager.getFlightsFrom(Places.WARSAW);

        //then
        assertThat(flights).containsOnly(flight, flight2);
    }

    @Test
    public void shouldFindToFlight() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setFrom(
            Places.WARSAW).setTo(Places.CHICAGO).build();
        Flight flight2 = new FlightBuilder().setFlightName("KIO876").setFrom(
            Places.WARSAW).setTo(Places.TORONTO).build();
        Flight flight3 = new FlightBuilder().setFlightName("BIG820").setFrom(
            Places.NEW_YORK).setTo(Places.TOKIO).build();

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);
        manager.addFlight(flight2);
        manager.addFlight(flight3);

        //when
        Set<Flight> flights = manager.getFlightsTo(Places.TOKIO);

        //then
        assertThat(flights).containsOnly(flight3);
    }

    @Test
    public void shouldFindFromToFlight() {
        //given
        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setDate(new Date()).setFrom(
            Places.WARSAW).setTo(Places.CHICAGO).build();
        Flight flight2 = new FlightBuilder().setFlightName("KIO876").setDate(new Date()).setFrom(
            Places.WARSAW).setTo(Places.TORONTO).build();
        Flight flight3 = new FlightBuilder().setFlightName("BIG820").setDate(new Date()).setFrom(
            Places.NEW_YORK).setTo(Places.TOKIO).build();

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);
        manager.addFlight(flight2);
        manager.addFlight(flight3);

        //when
        Set<Flight> flights = manager.getFlightsFromTo(Places.NEW_YORK, Places.TOKIO);

        //then
        assertThat(flights).containsOnly(flight3);
    }


    @Test
    public void shouldGetSeatsAvaragePrices() {

        //given
        HashMap<SeatClass, Double> expectedPrices = new HashMap<SeatClass, Double>();
        expectedPrices.put(SeatClass.ECONOMY, 2d);
        expectedPrices.put(SeatClass.BUSINESS, 4d);
        expectedPrices.put(SeatClass.FIRST, 6d);

        Flight flight = new FlightBuilder().setFlightName(FLIGHT_NAME).setAvailableSeats(3).build();
        flight.addSeat(new SeatBuilder().setPrice(1d).buildEconomySeat());
        flight.addSeat(new SeatBuilder().setPrice(3d).buildEconomySeat());

        flight.addSeat(new SeatBuilder().setPrice(3d).buildBusinessSeat());
        flight.addSeat(new SeatBuilder().setPrice(5d).buildBusinessSeat());

        flight.addSeat(new SeatBuilder().setPrice(5d).buildFirstSeat());
        flight.addSeat(new SeatBuilder().setPrice(7d).buildFirstSeat());


        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        //when
        HashMap<SeatClass, Double> prices = manager.getClassAvaragePricesInFlight(FLIGHT_NAME);

        //then
        assertThat(prices).isSameAs(expectedPrices);
    }

}
