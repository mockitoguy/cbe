package foo;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class FlightManagerTest {

    @Test
    public void shouldKnowAvailableSeats() {
        // given
        FlightManager manager = new FlightManager();
        int availableSeats = 10;
        String flightCode = "LL-123";

        manager.addFlight(flightCode, new Flight(availableSeats, 0));
        manager.addFlight("some-othe-flight", new Flight(123, 10));

        // when
        int seats = manager.getAvailableSeats(flightCode);

        // then
        assertEquals(availableSeats, seats);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldFailForUnknownFlight() throws Exception {
        // given
        FlightManager manager = new FlightManager();
        manager.addFlight("AAA", new Flight(1, 2));

        // when
        manager.getFlightInfo("some-flight");

    }

    @Test
    public void shouldFindCheapestSeatInFlight() {
        // given
        float cheapestPrice = 120.8f;
        int availableSeats = 12;
        FlightManager manager = new FlightManager();
        Flight fi = new Flight(availableSeats, cheapestPrice);
        Flight fi2 = new Flight(0, 453);
        manager.addFlight("DIY-1020", fi);
        manager.addFlight("DIY-1021", fi2);
        // when
        float price = manager.getCheapestSeat("DIY-1020");
        // then
        assertEquals(cheapestPrice, price);
    }


}
