import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * @author: pgrela
 */
public class FlightManagerTest {
    @Test
    public void shouldTellNumberOfAvailableSeatsOnTheFlight() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight = Flight.createBuilder().withFlightCode("FR8723").withSeatsQuantity(5).build();
        flightManager.addFlight(flight);

        //when
        int quantity = flightManager.getFlight("FR8723").getSeats();

        //then
        assertEquals(5, quantity);
    }

    @Test
    public void shouldReturnFlightsOnOriginDestinationDate() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = Flight.createBuilder().withFlightCode("FR2222").withOrigin("Warsaw").withDestination
            ("Chicago")
            .build();
        Flight flight2 = Flight.createBuilder().withFlightCode("FR2223").withOrigin("Warsaw").withDestination
            ("Poznan")
            .build();
        Flight flight3 = Flight.createBuilder().withFlightCode("FR2224").withOrigin("Warsaw").withDestination
            ("Chicago")
            .build();
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);


        //when
        List<Flight> flights = flightManager.getFlightsBetween("Warsaw", "Chicago");

        //then
        assertThat(flights).containsOnly(flight1, flight3);
    }

    @Test
    public void shouldReturnFlightsFromGivenOrigin() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = Flight.createBuilder().withFlightCode("FR2222").withOrigin("Warsaw").build();
        Flight flight2 = Flight.createBuilder().withFlightCode("FR2223").withOrigin("Berlin").build();
        Flight flight3 = Flight.createBuilder().withFlightCode("FR2224").withOrigin("Warsaw").build();
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);

        //when
        List<Flight> flights = flightManager.getFlightsFrom("Warsaw");

        //then
        assertThat(flights).containsOnly(flight1, flight3);
    }

    @Test
    public void shouldReturnFlightsFromGivenDestination() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = Flight.createBuilder().withFlightCode("FR2222").withDestination("Chicago").build();
        Flight flight2 = Flight.createBuilder().withFlightCode("FR2223").withDestination("Warsaw").build();
        Flight flight3 = Flight.createBuilder().withFlightCode("FR2224").withDestination("Chicago").build();
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);


        //when
        List<Flight> flights = flightManager.getFlightsTo("Chicago");

        //then
        assertThat(flights).containsOnly(flight1, flight3);
    }

}
