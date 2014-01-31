import org.junit.Test;

import java.math.BigDecimal;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static java.math.BigDecimal.valueOf;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:54
 */
public class FlightTest {

    @Test
    public void shouldKnowCheapestSeat() throws Exception {
        //given
        Flight flight = new FlightBuilder()
                .addSeatWithPrice(valueOf(10))
                .addSeatWithPrice(valueOf(20))
                .addSeatWithPrice(valueOf(30))
                .build();

        //then
        BigDecimal price = flight.getCheapestSeatPrice();

        //then
        assertThat(price).isEqualByComparingTo(valueOf(10));
    }

    @Test
    public void shouldThrowExceptionWhenFlightHasNoSeats()throws Exception {
        //given
        Flight flight = new FlightBuilder().build();

        //when
        when(flight).getCheapestSeatPrice();

        //then
        then(caughtException()).isInstanceOf(FlightHasNoSeatsException.class);
    }

    @Test
    public void shouldBookSeat()throws Exception {
        // given
        Flight flight = new FlightBuilder().addNumberOfSeats(3).build();

        // when
        flight.bookSeat("1");

        // then
        assertThat(flight.getAvailableSeats()).isEqualTo(2);
    }

    @Test
    public void shouldReturAveragePrice()throws Exception {
        //given
        Flight flight = new FlightBuilder()
                .addSeatWithPrice(valueOf(10))
                .addSeatWithPrice(valueOf(11))
                .addSeatWithPrice(valueOf(12))
                .build();

        // when
        BigDecimal price = flight.getAveragePrice();

        // then
        assertThat(price).isEqualByComparingTo(valueOf(11));
    }

    @Test
    public void shouldThrowExceptionWhenAskingForAverageSeatPrice()throws Exception {
        // given
        Flight flight = new FlightBuilder().build();

        // when
        when(flight).getAveragePrice();

        // then
        then(caughtException()).isInstanceOf(FlightHasNoSeatsException.class);
    }

    @Test
    public void shouldFlightMatchToOriginAndDestination() throws Exception {
        //given
        Flight flight = new FlightBuilder().setOrigin("London").setDestination("New York").build();

        //when
        boolean match = flight.matchOriginAndDestination("London", "New York");

        //then
        assertThat(match).isTrue();
    }

    @Test
    public void shouldFlightMatchToOrigin() throws Exception {
        // given
        Flight flight = new FlightBuilder().setOrigin("London").build();

        // when
        boolean match = flight.matchOrigin("London");

        // then
        assertThat(match).isTrue();
    }


    @Test
    public void shouldFlightMatchToDestination() throws Exception {
        // given
        Flight flight = new FlightBuilder().setDestination("Moscow").build();

        // when
        boolean match = flight.matchDestination("Moscow");

        // then
        assertThat(match).isTrue();
    }

}
