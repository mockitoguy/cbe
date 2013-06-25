import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class FlightManagerTest {

    private static final String WAW = "WAW";
    private static final String MAD = "MAD";
    private static final String SAMPLE_FLIGHT_NAME = "LH";
    private static final String INCORRECT_FLIGHT_NAME = "XXX";

    // ITERATION 1

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnNotExistingFlight() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME);

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).build();

        // when
        flightManager.getFlightByName(INCORRECT_FLIGHT_NAME);

        // then throws IllegalArgumentException

    }

    // ITERATION 2
    // story 1
    @Test
    public void shouldFindFlightsWithGivenOriginAndDestination() {
        // given
        Flight flight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME).setDestination(MAD).setOrigin(WAW);

        Flight otherFlight = new Flight().setFlightNo(SAMPLE_FLIGHT_NAME).setDestination("yyy").setOrigin("xxx");

        FlightManager flightManager = new FlightManager.Builder().addFlight(flight).addFlight(otherFlight)
                .addFlight(flight).build();

        SearchCondition searchCondition = new SearchCondition().setOrigin(WAW);

        // when

        List<Flight> flights = flightManager.getFlightsWithGivenCondition(searchCondition);

        // then
        Assertions.assertThat(flights.size()).isEqualTo(2);

    }

}
