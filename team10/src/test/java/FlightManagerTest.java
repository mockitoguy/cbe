import com.googlecode.catchexception.CatchException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightManagerTest {

    private static final String FLIGHT_NAME = "XP001`";

    @Mock
    FlightDAO flightDAO;

    @InjectMocks
    private FlightManager flightManager = new FlightManager(flightDAO);

    @Before
    public void setUp() throws Exception {
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(new Flight(4, 100d));
    }

    @Test
    public void shouldTellAvailableSeatsCount() throws Exception {

        //given
        //when
        int count = flightManager.getAvailableSeatsCount(FLIGHT_NAME);

        //then
        assertEquals(4, count);

    }

    @Test
    public void shouldTellPriceOfSeatInGivenFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 120d);

        //when
        double price = flightManager.getSeatPrice(FLIGHT_NAME, 1);

        //then
        assertEquals(120d, price, 0.1d);

    }

    @Test
    public void shouldTellPriceOfCheapestSeatInGivenFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 100d);
        flightManager.addSeatPrice(FLIGHT_NAME, 2, 60d);
        flightManager.addSeatPrice(FLIGHT_NAME, 3, 60d);
        //when
        double price = flightManager.getCheapestSeatPrice(FLIGHT_NAME);

        //then
        assertEquals(60d, price, 0.1d);
    }


    @Test
    public void shouldBookSeatOnGivenFlight() {
        //given
        //when
        flightManager.reserveSeatInFlight(4, FLIGHT_NAME);

        //then
        boolean isBooked = flightManager.isSeatInFlightReserved(4, FLIGHT_NAME);
        assertTrue(isBooked);
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnNonBookedFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 20d);
        flightManager.addSeatPrice(FLIGHT_NAME, 2, 20d);
        //when

        Double price = flightManager.getAveragePriceInFlight(FLIGHT_NAME);

        //then
        assertThat(price).isEqualTo(60d);
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnPartialBookedFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_NAME, 3, 20d);
        flightManager.addSeatPrice(FLIGHT_NAME, 4, 100d);
        flightManager.reserveSeatInFlight(1, FLIGHT_NAME);
        flightManager.reserveSeatInFlight(2, FLIGHT_NAME);
        //when

        Double price = flightManager.getAveragePriceInFlight(FLIGHT_NAME);

        //then
        assertThat(price).isEqualTo(60d);
    }
}
