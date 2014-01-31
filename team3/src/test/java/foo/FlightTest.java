package foo;

import org.fest.assertions.Delta;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class FlightTest {

    @Test
    public void shouldBookSeatForGivenFlight() {
        // given
        Flight flight = new Flight(2, 1);

        // when
        int seatNumber = flight.bookSeat();

        // then
        assertThat(seatNumber).isIn(1, 2);
    }

    @Test(expected = NoSeatsAvailableException.class)
    public void shouldFailWhenThereAreNoFreeSeats() {
        // given
        Flight flight = new Flight(1, 1);
        flight.bookSeat();

        // when
        flight.bookSeat();

        // then declared exception will be thrown
    }

    @Test
    public void shouldCalculateAverageFreeSeatPrice() {
        // given
        Flight flight = new Flight(1,2,3);
        flight.bookSeat();

        // when
        float avgPrice = flight.getAverageFreeSeatPrice();

        // then
        assertThat(avgPrice).isEqualTo(2.5f, Delta.delta(0.00001));
    }


}
