import org.junit.Test;

import java.util.List;

import static com.googlecode.catchexception.apis.CatchExceptionBdd.thenThrown;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlightManagerTest {

    FlightManager flightManager = new FlightManager();

    @Test
    public void shouldKnowAvailableSeats() {
        //given

        flightManager.addFlight(FlightBuilder.flightBuilder().flightNo("RA-666").setInitialCapacity(15).build());
        flightManager.addFlight(new FlightBuilder().flightNo("LOT-123").setInitialCapacity(5).build());

        //when
        int seatsNumber = flightManager.getAvailableSeats("LOT-123");

        //then
        assertEquals(5, seatsNumber);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldReturnExceptionIfFlightNoExists() {
        flightManager.getAvailableSeats("NON-EXISTING-FLIGHT");
    }

    @Test
    public void shouldThrowFlightAlreadyExistsExceptionWhenTryingToAddExistingFlight() {
        //given
        flightManager.addFlight(new FlightBuilder().flightNo("RA-666").build());

        //when
        when(flightManager).addFlight(new FlightBuilder().flightNo("RA-666").build());

        //then
        thenThrown(FlightAlreadyExistsException.class);
    }

    @Test
    public void shouldGetTheCheapestSeatPriceForAGivenFlight() {
        //given
        flightManager.addFlight(new FlightBuilder().flightNo("RA-222").build());
        flightManager.setPrice("RA-222", 1, 25);

        flightManager.addFlight(new FlightBuilder().flightNo("RA-666").build());
        flightManager.setPrice("RA-666", 1, 50);
        flightManager.setPrice("RA-666", 2, 50);
        flightManager.setPrice("RA-666", 3, 70);

        //when
        List<Seat> cheapestSeats = flightManager.getCheapestSeats("RA-666");

        //then
        assertThat(cheapestSeats).isNotEmpty();
        assertThat(cheapestSeats.size()).isEqualTo(2);
        assertThat(cheapestSeats.get(0).getPrice()).isEqualTo(50);
    }

    @Test
    public void shouldBookASeatOnAGivenFlight() throws Throwable {
        //given
        flightManager.addFlight(new FlightBuilder().flightNo("RA-666").build());

        //when
        flightManager.bookASeat("RA-666", 1);

        //then
        assertFalse(flightManager.seatAt("RA-666", 1).isAvailable());
        assertTrue(flightManager.seatAt("RA-666", 2).isAvailable());

    }

    @Test
    public void shouldGetAListOfFlightsBeetweenTwoLocations() throws Throwable {
        //given
        Flight flight1 = new FlightBuilder().between("London", "New York").build();
        Flight flight2 = new FlightBuilder().between("London", "New York").build();

        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);

        flightManager.addFlight(new FlightBuilder().between("Warsaw", "New York").build());
        flightManager.addFlight(new FlightBuilder().between("London", "San Francisco").build());

        //when
        List<FlightDetails> details = flightManager.getFlightsDetails("London", "New York");

        //then
        assertThat(details).containsOnly(flight1, flight2);
    }


}
