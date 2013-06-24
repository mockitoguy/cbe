import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    private final FlightManager flightManager = new FlightManager();

    @Before
    public void setUp() throws Exception {
        flightManager.addFlight("LH101", 5);
        flightManager.addFlight("AE500", 15);
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
    public void shouldTellPriceOfCheapestSeatInGivenFlight() {
        //given
        flightManager.addSeatPrice("LH101", 1, 100d);
        flightManager.addSeatPrice("LH101", 2, 60d);
        flightManager.addSeatPrice("LH101", 3, 60d);
        flightManager.addSeatPrice("AE500", 1, 160d);
        //when
        double price = flightManager.getSeatPrice("LH101", 1);

        //then
        assertEquals(100d, price, 0.1d);
    }
}
