package cbe.flights;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author tskrobol
 */
public class FlightManagerTest {

    public static final String TU_128 = "TU128";
    public static final int AVAILABLE_SEATS = 5;

    @Test
    public void shouldTellAvailableSeatsCount() {
        //given
        FlightManager manager = new FlightManager();
        manager.addFlight(new Flight(TU_128, AVAILABLE_SEATS));
        manager.addFlight(new Flight("LO888",44));

        //when
        int seatsCount = manager.getAvailableSeatsCount(TU_128);

        //then
        assertEquals(AVAILABLE_SEATS, seatsCount);
    }

    @Test
    public void shouldTellAvailableSeatsCountForUnknownFligt() {
        //given
        FlightManager manager = new FlightManager();

        //when
        int seatsCount = manager.getAvailableSeatsCount(TU_128);

        //then
        assertEquals(0, seatsCount);
    }

    @Test
    public void shouldGetCheapestSeatsInFlight(){
        //given
        Flight flight = new Flight(TU_128, AVAILABLE_SEATS);
        flight.setSeatPrice(0, 2d);
        flight.setSeatPrice(1, 3d);
        flight.setSeatPrice(2, 4d);
        flight.setSeatPrice(3, 1d);
        flight.setSeatPrice(4, 6d);

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        //when
        Seat cheapestSeat = manager.getCheapestSeat(TU_128);

        //then
        assertEquals(1d, cheapestSeat.getPrice(), 0.01);
    }

    @Test
    public void shouldBookSeat(){
        //given
        Flight flight = new Flight(TU_128, AVAILABLE_SEATS);
        flight.setSeatPrice(0, 2d);
        flight.setSeatPrice(1, 3d);
        flight.setSeatPrice(2, 4d);
        flight.setSeatPrice(3, 1d);
        flight.setSeatPrice(4, 6d);

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        User user = new User("Tomek");

        //when
        boolean booked = manager.bookSeatInFlight(TU_128, 3, user);

        //then
        assertTrue(booked);
    }

    @Test
    public void shouldGetNotBookedSeatAvaragePrice(){
        //given
        Flight flight = new Flight(TU_128, AVAILABLE_SEATS);
        flight.setSeatPrice(0, 2d);
        flight.setSeatPrice(1, 2d);
        flight.setSeatPrice(2, 4d);
        flight.setSeatPrice(3, 1d);
        flight.setSeatPrice(4, 4d);

        FlightManager manager = new FlightManager();
        manager.addFlight(flight);

        User user = new User("Tomek");
        boolean booked = manager.bookSeatInFlight(TU_128, 3, user);

        //when
        double avaragePrice = manager.getNonBookedAvaragePrice(TU_128);

        //then
        assertEquals(3d, avaragePrice, 0.01);
    }



}
