import org.junit.Test;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class FlightTest {

    @Test
    public void shouldTellSeatCount() {
        Flight flight = new FlightBuilder().setSeatsCount(4).createFlight();

        //then
        assertThat(flight.getSeatsCount()).isEqualTo(4);
    }


    @Test
    public void shouldReserveSeat() {
        //when
        Flight flight = new FlightBuilder().setSeatsCount(2).createFlight();
        flight.reserveSeat(2);

        //then
        assertTrue(flight.isSeatReserved(2));
    }

    @Test
    public void shouldFailOnReservedSeat() {
        //given
        Flight flight = new FlightBuilder().setSeatsCount(2).createFlight();

        //when
        flight.reserveSeat(2);
        when(flight).reserveSeat(2);

        //then
        then(caughtException()).isInstanceOf(SeatAlreadyReserved.class);

    }


    @Test
    public void shouldGetAveragePriceOnFreeFlight() {

        //given
        Flight flight = new FlightBuilder().setSeatsCount(2).setDefaultPrice(20d).createFlight();

        //when
        double price = flight.getAveragePrice();

        //then
        assertThat(price).isEqualTo(20d);
    }

    @Test
    public void shouldGetAveragePriceOnNonFreeFlight() {

        //given
        Flight flight = new FlightBuilder().setSeatsCount(3).setDefaultPrice(20d).createFlight();
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
        Flight flight = new FlightBuilder().setSeatsCount(3).setDefaultPrice(10d).createFlight();
        flight.setSeatPrice(3, 30d);
        flight.reserveSeat(1);

        //when

        double price = flight.getAveragePrice();

        //then
        assertThat(price).isEqualTo(20d);
    }

}
