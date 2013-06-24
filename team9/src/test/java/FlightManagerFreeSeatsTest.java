import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FlightManagerFreeSeatsTest {

    @Test
    public void shoudTellAvailableSeatsCount() throws Exception {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", 5);
        flightManager.addFlight("AE500", 15);

        //when
        int count = flightManager.getAvailableSeatsCount("LH101");

        //then
        assertEquals(5, count);
    }

    @Test
    public void shoudFailToTellAvailableSeatsCountOnNonExistingFlight() throws Exception {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", 5);
        flightManager.addFlight("AE500", 15);

        //when
        when(flightManager).getAvailableSeatsCount("LH102");

        //then
        then(caughtException())
            .isInstanceOf(FlightNotFoundException.class);
    }
}
