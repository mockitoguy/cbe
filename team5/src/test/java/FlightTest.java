import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:54
 */
public class FlightTest {

    @Test
    public void shouldKnowCheapestSeat() {
        //given
        Flight flight = FlightTestDataGenerator.flightWithCheapestPrice("LOT-133", BigDecimal.valueOf(100));

        //then
        BigDecimal price = flight.getCheapestSeatPrice();

        //then
        assertThat(price).isEqualByComparingTo(BigDecimal.valueOf(100));
    }


    @Test(expected = FlightHasNoSeatsException.class)
    public void shouldThrowExceptionWhenFlightHasNoSeats() {
        //given
        Flight flight = FlightTestDataGenerator.flightWithSeats("LOT-111",0);

        //when
        flight.getCheapestSeatPrice();

        //then
        //throw FlightHasNoSeatsException

    }

    @Test
    public void shouldBookSeat() {
        // given
        Flight flight = FlightTestDataGenerator.flightWithSeats("LOT-123", 3);

        // when
        flight.bookSeat("1");

        // then
        assertThat(flight.getAvailableSeats()).isEqualTo(2);
    }

}
