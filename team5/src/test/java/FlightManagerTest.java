import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:26
 */
public class FlightManagerTest {

    @Test
    public void shouldUserKnowNumberOfAvailableSeatsForFlight() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flightToNewYork = new Flight("LOT-123");
        flightToNewYork.addSeat("1a", BigDecimal.TEN);
        flightToNewYork.addSeat("1b", BigDecimal.TEN);
        flightManager.addFlight(flightToNewYork);

        Flight flightToLondon = new Flight("LOT-456");
        flightToLondon.addSeat("2a", BigDecimal.TEN);
        flightToLondon.addSeat("3a", BigDecimal.TEN);
        flightToLondon.addSeat("4a", BigDecimal.TEN);
        flightManager.addFlight(flightToLondon);

        //when
        int seats = flightManager.getAvailableSeatsForFlight("LOT-123");

        //then
        assertThat(seats).isEqualTo(2);
    }

    @Test
    public void shouldUserGetNoSeatsWhenFlightNotExists() {
        // given
        FlightManager manager = new FlightManager();
        manager.addFlight(new Flight("LOT-QWE"));
        String notExistingFlightNumber = "abc";

        // when
        int seats = manager.getAvailableSeatsForFlight(notExistingFlightNumber);

        // then
        assertThat(seats).isZero();
    }

    @Test
    public void shouldUserGetCheapestSeatForGivenFlight() {
        // given
        FlightManager manager = new FlightManager();
        Flight flightToLondon = new Flight("LOT-123");
        flightToLondon.addSeat("0", BigDecimal.ONE);
        flightToLondon.addSeat("1", BigDecimal.TEN);
        manager.addFlight(flightToLondon);

        // when
        BigDecimal price = manager.getCheapestSeatForFlight("LOT-123");

        // then
        assertThat(price).isEqualTo(BigDecimal.ONE);
    }
}
