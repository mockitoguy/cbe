import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class FlightTest {

    private static final String SAMPLE_FLIGHT_NAME = "LO";
    private static final int SAMPLE_SEAT_NUMBER = 123;
    private static final int MIN_PRICE = 100;
    private static final int EXPECTED_SEAT_COUNT = 2;

    // ITERATION 1
    // story 1
    @Test
    public void shouldReturnAvailableSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.addSeat(new Seat(0, 0));
        }

        // when

        List<Seat> availableSeats = flight.getAvaliableSeats();
        int seatsCount = availableSeats.size();

        // then

        assertEquals(EXPECTED_SEAT_COUNT, seatsCount);
    }

    // ITERATION 1
    // story 2
    @Test
    public void shouldReturnCheapestSeatPrice() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.addSeat(new Seat(i, MIN_PRICE + i));
        }

        // when
        Seat seat = flight.getCheapestSeats();

        // then
        assertEquals(MIN_PRICE, seat.price);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailOnFlightWithoutSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        // when
        flight.getCheapestSeats();

        // then throws IllegalStateExcpetion
    }

    // ITERATION 1
    // story 3
    @Test
    public void shouldBookSeatOnFlight() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        flight.addSeat(new Seat(SAMPLE_SEAT_NUMBER, MIN_PRICE));

        // when
        Seat seat = flight.getSeatOnFlight(SAMPLE_SEAT_NUMBER).book();

        // then
        assertTrue(seat.isBooked);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailOnDoubleBooking() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        flight.addSeat(new Seat(SAMPLE_SEAT_NUMBER, MIN_PRICE));

        // when
        flight.getSeatOnFlight(SAMPLE_SEAT_NUMBER).book().book();

        // then throws IllegalArgumentException

    }

    // ITERATION 1
    // story 4
    @Test
    public void shouldReturnAvaragePriceOfNonBookedSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME).addSeat(new Seat(1, 100))
                .addSeat(new Seat(2, 200)).addSeat(new Seat(2, 600).book());

        // when
        double avaragePrice = flight.getAvaragePriceOfNonBookedSeats();

        // then
        Assertions.assertThat(avaragePrice).isEqualTo(150f);

    }

}
