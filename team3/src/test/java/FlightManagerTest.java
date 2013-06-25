import org.junit.Test;

public class FlightManagerTest {

    private static final int SAMPLE_SEAT_NUMBER = 1;
    private static final int MIN_PRICE = 100;
    private static final int EXPECTED_SEAT_COUNT = 5;
    private static final String SAMPLE_FLIGHT_NAME = "LH";
    private static final String INCORRECT_FLIGHT_NAME = "XXX";

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNotExistingFlight() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        flightManager.getFlightByName(INCORRECT_FLIGHT_NAME);

        // then throws IllegalArgumentException

    }

}
