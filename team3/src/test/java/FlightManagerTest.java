import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FlightManagerTest {

    private static final int MIN_PRICE = 100;
    private static final int EXPECTED_SEAT_COUNT = 5;
    private static final String SAMPLE_FLIGHT_NAME = "LH";
    private static final String INCORRECT_FLIGHT_NAME = "XXX";

    @Test
    public void shouldReturnAvailableSeats() {
        // given
        Flight flight = new Flight();
        flight.number = SAMPLE_FLIGHT_NAME;
        flight.seat = new ArrayList<Seat>();

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.seat.add(new Seat(0, 0));
        }

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();
        // when

        List<Seat> availableSeats = flightManager.getAvaliableSeatsForFlight(SAMPLE_FLIGHT_NAME);
        int seatsCount = availableSeats.size();

        // then

        assertEquals(EXPECTED_SEAT_COUNT, seatsCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNotExistingFlight() {
        // given
        Flight flight = new Flight();
        flight.number = SAMPLE_FLIGHT_NAME;

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        flightManager.getAvaliableSeatsForFlight(INCORRECT_FLIGHT_NAME);

        // then throws IllegalArgumentException

    }

    @Test
    public void shouldReturnCheapestSeatPrice() {
        // given
        Flight flight = new Flight();
        flight.number = SAMPLE_FLIGHT_NAME;
        flight.seat = new ArrayList<Seat>();

        for (int i = 0; i < EXPECTED_SEAT_COUNT; i++) {
            flight.seat.add(new Seat(i, MIN_PRICE + i));
        }

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        Seat seat = flightManager.getCheapestSeatsForFlight(SAMPLE_FLIGHT_NAME);

        // then
        assertEquals(MIN_PRICE, seat.price);
    }

}
