import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightManagerSeatAvgPriceTest {

    private static final boolean SEAT_RESERVED = true;

    @Test
    public void shouldTellCheapestSeatInGivenFlight() throws Exception {
        //given
        Flight flight = new FlightParametersBuilder()
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", flight);

        //when
        int avgPrice = flightManager.getAvgSeatPrice("LH101");

        //then
        assertEquals(2, avgPrice);
    }

    @Test
    public void shoudFailToTellAvgSeatPriceWhenNoAvailable() throws Exception {
        //given
        Flight flight = new FlightParametersBuilder()
            .addSeat(1, SEAT_RESERVED)
            .addSeat(2, SEAT_RESERVED)
            .addSeat(3, SEAT_RESERVED)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", flight);

        //when
        when(flightManager).getAvgSeatPrice("LH101");

        //then
        then(caughtException())
            .isInstanceOf(NoFreeSeatsException.class);
    }
}
