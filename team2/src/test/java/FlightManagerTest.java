import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    FlightManager flightManager;

    @Before
    public void setup() {
        flightManager = new FlightManager();
    }


    @Test
    public void shouldKnowAvailableSeats() {
        //given
        flightManager.addFlight(new Flight("RA-666", 15));
        flightManager.addFlight(new Flight("LOT-123", 5));

        //when
        int seatsNumber = flightManager.getAvailableSeats("LOT-123");

        //then
        assertEquals(5, seatsNumber);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldReturnExceptionIfFlightNoExists() {
        //when
        flightManager.getAvailableSeats("NON-EXISTING-FLIGHT");
    }

    @Test(expected = FlightAlreadyExistsException.class)
    public void shouldThrowFlightAlreadyExistsExceptionWhenTryingToAddExistingFlight() {
        //given
        flightManager.addFlight(new Flight("RA-666", 15));

        //when
        flightManager.addFlight(new Flight("RA-666", 15));
    }

    @Test
    public void shouldGetTheCheapestSeatPriceForAGivenFlight() throws Throwable {
        //given

        //when

        //then

    }


}
