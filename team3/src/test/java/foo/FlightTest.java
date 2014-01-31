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
        Flight flight = Flight.withSeatPrices(1.0f, 1.0f).build();

        // when
        int seatNumber = flight.bookSeat();

        // then
        assertThat(seatNumber).isEqualTo(1);
    }

    @Test(expected = NoSeatsAvailableException.class)
    public void shouldFailWhenThereAreNoFreeSeats() {
        // given
        Flight flight = Flight.withSeatPrices(1).build();
        flight.bookSeat();

        // when
        flight.bookSeat();

        // then declared exception will be thrown
    }

    @Test
    public void shouldCalculateAverageFreeSeatPrice() {
        // given
        Flight flight = Flight.withSeatPrices(1, 2, 3).build();
        flight.bookSeat();

        // when
        float avgPrice = flight.getAverageFreeSeatPrice();

        // then
        assertThat(avgPrice).isEqualTo(2.5f, Delta.delta(0.00001));
    }


}
