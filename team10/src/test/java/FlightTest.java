import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class FlightTest {

    private final Flight flight = new Flight(4, 20d);

    @Test
    public void shouldTellSeatCount() {
        //given
        //when


        //then
        Assert.assertEquals(4, flight.getSeatsCount());
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



    @Test
    public void shouldGetAveragePriceOnFreeFlight() {

        //given
        //when

        double price = flight.getAveragePrice();

        //then
        assertThat(price).isEqualTo(20d);
    }

    @Test
    public void shouldGetAveragePriceOnNonFreeFlight() {

        //given
        flight.reserveSeat(1);
        flight.reserveSeat(2);
        //when

        double price = flight.getAveragePrice();

        //then
        assertThat(price).isEqualTo(20d);
    }

    @Test
    public void shouldGetAveragePriceOnNonFreeFlightWithDiffrentPrices() {

        //given
        flight.setSeatPrice(3, 50d);
        flight.setSeatPrice(4, 60d);
        flight.reserveSeat(1);
        flight.reserveSeat(2);

        //when

        double price = flight.getAveragePrice();

        //then
        assertThat(price).isEqualTo(55d);
    }
}
