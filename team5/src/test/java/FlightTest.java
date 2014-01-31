import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:54
 */
public class FlightTest {

    @Test
    public void shoudKnowPriceForSeat() {
        //given
        Flight flight = new Flight("LOT-123");
        flight.addSeat("1a", BigDecimal.valueOf(555.55));

        //when
        BigDecimal price = flight.getSeatPrice("1a");

        //then
        assertThat(price).isEqualByComparingTo(BigDecimal.valueOf(555.55));
    }


    @Test
    public void shuldKnowCheapestSeat() {
        //given
        Flight flight = new Flight("LOT-133");
        flight.addSeat("11", BigDecimal.valueOf(100));
        flight.addSeat("12", BigDecimal.valueOf(101));
        flight.addSeat("13", BigDecimal.valueOf(103));

        //then
        BigDecimal price = flight.getCheapestSeatPrice();

        //then
        assertThat(price).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

}
