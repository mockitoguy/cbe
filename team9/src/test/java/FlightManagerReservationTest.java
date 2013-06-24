import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.gurzkowski
 * Date: 24.06.13
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class FlightManagerReservationTest {

    @Test
    public void shouldReseveFreeSeat() throws Exception {
        //given
        Flight flight = new FlightParametersBuilder()
            .flightNo("LH101")
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .addSeat(4)
            .addSeat(5)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flight);

        //when
        int seatNumber = 2;
        boolean isSuccess = flightManager.bookSelectedSeat("LH101", seatNumber);

        //then
        assertTrue(isSuccess);
    }

    @Test
    public void shouldFailReseveReservedSeat() throws Exception {
        //given
        Flight flight = new FlightParametersBuilder()
            .flightNo("LH101")
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .addSeat(4)
            .addSeat(5)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flight);

        //when
        int seatNumber = 2;
        flightManager.bookSelectedSeat("LH101", seatNumber);
        boolean isSuccess = flightManager.bookSelectedSeat("LH101", seatNumber);

        //then
        assertFalse(isSuccess);
    }

    @Test
    public void shouldFailReservingNonExistingSeat() throws Exception {
        //given
        Flight flight = new FlightParametersBuilder()
            .flightNo("LH101")
            .addSeat(1)
            .addSeat(2)
            .addSeat(3)
            .addSeat(4)
            .addSeat(5)
            .build();

        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(flight);

        //when
        int seatNumber = 6;
        when(flightManager).bookSelectedSeat("LH101", seatNumber);

        //then
        then(caughtException())
            .isInstanceOf(SeatNotFoundException.class);
    }
}
