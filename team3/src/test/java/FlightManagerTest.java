import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class FlightManagerTest {

    private static final int SAMPLE_SEAT_NUMBER = 1;
    private static final int MIN_PRICE = 100;
    private static final int EXPECTED_SEAT_COUNT = 5;
    private static final String SAMPLE_FLIGHT_NAME = "LH";
    private static final String INCORRECT_FLIGHT_NAME = "XXX";

    // ITERATION 1
    // story 1
    @Test
    public void shouldReturnAvailableSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.seats.add(new Seat(0, 0));
        }

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();
        // when

        List<Seat> availableSeats = flightManager.getFlightByName(SAMPLE_FLIGHT_NAME).getAvaliableSeats();
        int seatsCount = availableSeats.size();

        // then

        assertEquals(EXPECTED_SEAT_COUNT, seatsCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNotExistingFlight() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        flightManager.getFlightByName(INCORRECT_FLIGHT_NAME).getAvaliableSeats();

        // then throws IllegalArgumentException

    }

    // ITERATION 1
    // story 2
    @Test
    public void shouldReturnCheapestSeatPrice() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.seats.add(new Seat(i, MIN_PRICE + i));
        }

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        Seat seat = flightManager.getFlightByName(SAMPLE_FLIGHT_NAME).getCheapestSeats();

        // then
        assertEquals(MIN_PRICE, seat.price);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailOnFlightWithoutSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        flightManager.getFlightByName(SAMPLE_FLIGHT_NAME).getCheapestSeats();

        // then throws IllegalStateExcpetion
    }

    // ITERATION 1
    // story 3
    @Test
    public void shouldBookSeatOnFlight() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        flight.seats.add(new Seat(SAMPLE_SEAT_NUMBER, MIN_PRICE));

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        Seat seat = flightManager.getFlightByName(SAMPLE_FLIGHT_NAME).getSeatOnFlight(SAMPLE_SEAT_NUMBER).book();

        // then
        assertTrue(seat.isBooked);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailOnDoubleBooking() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        flight.seats.add(new Seat(SAMPLE_SEAT_NUMBER, MIN_PRICE));

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();
        Flight flightByName = flightManager.getFlightByName(SAMPLE_FLIGHT_NAME);

        // when
        flightByName.getSeatOnFlight(SAMPLE_SEAT_NUMBER).book().book();

        // then throws IllegalArgumentException

    }

    // ITERATION 1
    // story 4
    @Test
    public void shouldReturnAvaragePriceOfNonBookedSeats() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME).addSeat(new Seat(1, 100))
                .addSeat(new Seat(2, 200)).addSeat(new Seat(2, 600).book());

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when

        double avaragePrice = flightManager.getFlightByName(SAMPLE_FLIGHT_NAME).getAvaragePriceOfNonBookedSeats();

        // then
        Assertions.assertThat(avaragePrice).isEqualTo(150f);

    }

    // ITERATION 2

}
