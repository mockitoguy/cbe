import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightManagerCheapSeatsTest {

    private static final boolean SEAT_RESERVED = true;

    @Test
    public void shouldTellCheapestSeatInGivenFlight() throws Exception {
        //given
        FlightParameters flightParameters = new FlightParametersBuilder()
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .addSeat(4)
            .addSeat(5)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", flightParameters);

        //when
        int lowestPrice = flightManager.getCheapestSeatPrice("LH101");

        //then
        assertEquals(1, lowestPrice);
    }


    @Test
    public void shoudFailToTellCheapestSeatPriceWhenNoAvailable() throws Exception {
        //given
        FlightParameters flightParameters = new FlightParametersBuilder()
            .addSeat(1, SEAT_RESERVED)
            .addSeat(2, SEAT_RESERVED)
            .addSeat(3, SEAT_RESERVED)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", flightParameters);

        //when
        when(flightManager).getCheapestSeatPrice("LH101");

        //then
        then(caughtException())
            .isInstanceOf(NoFreeSeatsException.class);
    }

    @Test
    public void shoudFailToTellPriceWhenAllSeatsAreReserved() throws Exception {
        //given
        FlightParameters flightParameters = new FlightParametersBuilder()
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .addSeat(4)
            .addSeat(5)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", flightParameters);

        //when
        when(flightManager).getCheapestSeatPrice("LH102");

        //then
        then(caughtException())
            .isInstanceOf(FlightNotFoundException.class);
    }
}
