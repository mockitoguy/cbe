import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    @Test
    public void shouldBookSelectedSeatsOnGivenFlight() {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder().setName("LH101").addSeats(new Seat("123A", 100)).addSeats(new Seat("321B", 250)).build();
        flightManager.addFlight(flightByLh);

        // when
        Seat bookedSeat = flightManager.doBookingSeat("LH101", "123A");

        // then
        assertEquals("123A", bookedSeat.getSeatId());
    }

    @Test(expected = NoSeatAvailableException.class)
    public void shouldThrowExceptionIfBookingFailed() {

    }

    @Test
    public void shouldDecreaseNumberOfAvailableSeatsAfterBooking() {

    }

    @Test
    public void shouldTellAvailabaleSeatsCount() {
        // given
        Seat seat = new Seat(50);
        FlightManager flightManager = new FlightManager();
        Flight flightByLh = Flight.builder().setName("LH101").addSeats(seat).addSeats(seat).addSeats(seat).build();
        flightManager.addFlight(flightByLh);

        Flight flightByRh = Flight.builder().setName("RH101").addSeats(seat).addSeats(seat).addSeats(seat).build();
        flightManager.addFlight(flightByRh);

        // when
        int count = flightManager.getAvailableSeatsCount("LH101");

        // then
        assertEquals(3, count);
    }

    @Test(expected = NoFlightFoundException.class)
    public void shouldThrowExceptionWhenFlightDoesNotExistInGetAvailableSeatsCount() {
        // given
        FlightManager flightManager = new FlightManager();

        // when
        flightManager.getAvailableSeatsCount("nope");
    }

    @Test
    public void shouldGetACheapestSeat() {
        // given
        FlightManager flightManager = new FlightManager();
        Seat businessSeat = new Seat(100);
        Seat ecoSeat = new Seat(50);
        Flight flight = Flight.builder().setName("LH101").addSeats(businessSeat).addSeats(ecoSeat).build();
        flightManager.addFlight(flight);

        //when
        Seat cheapestSeat = flightManager.getCheapestSeat("LH101");

        //then
        assertEquals(50, cheapestSeat.getPrice());

    }

    @Test(expected = NoSeatFoundException.class)
    public void shouldThrowExceptionWhenNoSeats() {
        FlightManager flightManager = new FlightManager();
        Flight flight = Flight.builder().setName("LH101").build();
        flightManager.addFlight(flight);

        //when
        Seat cheapestSeat = flightManager.getCheapestSeat("LH101");

    }

    @Test(expected = NoFlightFoundException.class)
    public void shouldThrowExceptionWhenFlightDoesNotExistInGetCheapestSeat() {
        // given
        FlightManager flightManager = new FlightManager();

        // when
        flightManager.getCheapestSeat("nope");
    }

}
