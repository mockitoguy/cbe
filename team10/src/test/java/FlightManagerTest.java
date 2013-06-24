import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightManagerTest {

    private static final String FLIGHT_1 = "LH101";
    private static final String FLIGHT_2 = "XZ001";
    private final FlightManager flightManager = new FlightManager();

    @Before
    public void setUp() throws Exception {
        flightManager.addFlight(FLIGHT_1, 5, 80d);
        flightManager.addFlight(FLIGHT_2, 2, 20d);
        flightManager.addFlight("AE500", 15, 20d);
    }

    @Test
    public void shouldTellAvailableSeatsCount() throws Exception {

        //given
        //when
        int count = flightManager.getAvailableSeatsCount("LH101");

        //then
        assertEquals(5, count);

    }

    @Test
    public void shouldTellPriceOfSeatInGivenFlight() {
        //given
        flightManager.addSeatPrice("LH101", 1, 100d);
        flightManager.addSeatPrice("LH101", 2, 60d);

        //when
        double price = flightManager.getSeatPrice("LH101", 1);

        //then
        assertEquals(100d, price, 0.1d);

    }

    @Test
    public void shouldTellPriceOfCheapestSeatInGivenFlight() {
        //given
        flightManager.addSeatPrice("LH101", 1, 100d);
        flightManager.addSeatPrice("LH101", 2, 60d);
        flightManager.addSeatPrice("LH101", 3, 60d);
        flightManager.addSeatPrice("AE500", 1, 40d);
        //when
        double price = flightManager.getCheapestSeatPrice("LH101");

        //then
        assertEquals(60d, price, 0.1d);
    }


    @Test
    public void shouldBookSeatOnGivenFlight() {
        //given
        //when
        flightManager.reserveSeatInFlight(12, "AE500");

        //then
        boolean isBooked = flightManager.isSeatInFlightReserved(12, "AE500");
        assertTrue(isBooked);
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnNonBookedFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_2, 1, 100d);
        flightManager.addSeatPrice(FLIGHT_2, 2, 20d);
        //when

        Double price = flightManager.getAveragePriceInFlight(FLIGHT_2);

        //then
        assertThat(price).isEqualTo(60d);
    }
    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnPartialBookedFlight() {
        //given
        flightManager.addSeatPrice(FLIGHT_2, 1, 100d);
        flightManager.addSeatPrice(FLIGHT_2, 2, 34d);
        flightManager.reserveSeatInFlight(1, FLIGHT_2);
        //when

        Double price = flightManager.getAveragePriceInFlight(FLIGHT_2);

        //then
        assertThat(price).isEqualTo(34d);
    }
}
