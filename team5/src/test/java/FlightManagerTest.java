import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:26
 */
public class FlightManagerTest {

    FlightManager manager;

    @Before
    public void setup() {
        manager = new FlightManager();
    }

    @Test
    public void shouldUserKnowNumberOfAvailableSeatsForFlight() {
        //given
        manager.addFlight(FlightTestDataGenerator.flightWithSeats("LOT-123", 2));
        manager.addFlight(FlightTestDataGenerator.flightWithSeats("LOT-456", 3));

        //when
        int seats = manager.getAvailableSeatsForFlight("LOT-123");

        //then
        assertThat(seats).isEqualTo(2);
    }

    @Test(expected = NoFlightFoundException.class)
    public void shouldThrowExceptionWhenFlightNotExists() {
        // given
        manager.addFlight(FlightTestDataGenerator.flightWithSeats("LOT-123", 0));
        String notExistingFlightNumber = "abc";

        // when
        manager.getAvailableSeatsForFlight(notExistingFlightNumber);

        // then
        // NoFlightFoundException
    }



    @Test
    public void shouldUserGetCheapestSeatForGivenFlight() {
        // given
        manager.addFlight(FlightTestDataGenerator.flightWithCheapestPrice("LOT-123", BigDecimal.ONE));

        // when
        BigDecimal price = manager.getCheapestSeatForFlight("LOT-123");

        // then
        assertThat(price).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void shouldUserBookSeatForFlight() {
        //given
        String flightNumber = "LOT-333";
        manager.addFlight(FlightTestDataGenerator.flightWithSeats(flightNumber, "1", "2", "3"));

        //when
        manager.bookSeat(flightNumber,"3");

        //then
        assertThat(manager.getAvailableSeatsForFlight(flightNumber)).isEqualTo(2);
    }
}
