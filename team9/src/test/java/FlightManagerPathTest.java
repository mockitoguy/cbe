import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class FlightManagerPathTest {

    @Test
    public void shouldTellWhichFlightAreOnSpecifiedPath() throws Exception {
        // given
        Flight flightToPOZ = new FlightParametersBuilder().flightNo("LH101").origin("WAW").dest("POZ").build();
        Flight flightFromPOZ = new FlightParametersBuilder().flightNo("LH102").origin("POZ").dest("WAW").build();
        Flight flightToJFK = new FlightParametersBuilder().flightNo("LH103").origin("WAW").dest("JFK").build();

        FlightManager flightManager = new FlightManager().addFlight(flightToPOZ, flightToJFK, flightFromPOZ);

        // when
        List<Flight> results = flightManager.getFlightsBetween("WAW", "POZ");

        // then
       assertThat(results).containsOnly(flightToPOZ);
    }

    @Test
    public void shouldTellWhichFlightsAreOnSpecifiedPath() throws Exception {
        // given
        Flight flightToPOZ = new FlightParametersBuilder().flightNo("LH101").origin("WAW").dest("POZ").build();
        Flight secondFlightToPOZ = new FlightParametersBuilder().flightNo("LH102").origin("WAW").dest("POZ").build();
        Flight flightToJFK = new FlightParametersBuilder().flightNo("LH103").origin("WAW").dest("JFK").build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flightToPOZ, flightToJFK, secondFlightToPOZ);

        // when
        List<Flight> results = flightManager.getFlightsBetween("WAW", "POZ");

        // then
        assertThat(results).containsExactly(flightToPOZ, secondFlightToPOZ);
    }

    @Test
    public void shouldTellWhichFlightsAreFromSelectedOrigin() throws Exception {
        // given
        Flight flightFromWAWToJFK = new FlightParametersBuilder().flightNo("LH101").origin("WAW").dest("JFK").build();
        Flight flightFromWAWToPOZ = new FlightParametersBuilder().flightNo("LH102").origin("WAW").dest("POZ").build();

        Flight flightToWAW = new FlightParametersBuilder().flightNo("LH103").origin("JFK").dest("WAW").build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flightFromWAWToJFK, flightFromWAWToPOZ, flightToWAW);

        // when
        List<Flight> results = flightManager.getFlightsFrom("WAW");

        // then
        assertThat(results).containsExactly(flightFromWAWToJFK, flightFromWAWToPOZ);
    }

    @Test
    public void shouldTellWhichFlightsAreToSelectedDestination() throws Exception {
        // given
        Flight flightFromWAWToJFK = new FlightParametersBuilder().flightNo("LH101").origin("WAW").dest("JFK").build();
        Flight flightFromWAWToPOZ = new FlightParametersBuilder().flightNo("LH102").origin("WAW").dest("POZ").build();
        Flight flightToWAW = new FlightParametersBuilder().flightNo("LH103").origin("JFK").dest("WAW").build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flightFromWAWToJFK, flightFromWAWToPOZ, flightToWAW);

        // when
        List<Flight> results = flightManager.getFlightsTo("WAW");

        // then
        assertThat(results).containsOnly(flightToWAW);
    }
}
