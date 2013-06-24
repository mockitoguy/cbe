import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class FlightTest {

    private final Flight flight = new Flight(5);

    @Test
    public void shouldTellSeatCount() {
        //given
        //when


        //then
        Assert.assertEquals(5, flight.getSeatsCount());
    }

    @Test
    public void shouldReserveSeat() {
        //given
        //when

        flight.reserveSeat(2);

        //then
        assertTrue(flight.isSeatReserved(2));
    }

    @Test
    public void shouldFailOnReservedSeat() {
        //given
        //when

        flight.reserveSeat(2);
        catchException(flight).reserveSeat(2);

        //then
        assertThat(caughtException()).isInstanceOf(SeatAlreadyReserved.class);

    }
}
