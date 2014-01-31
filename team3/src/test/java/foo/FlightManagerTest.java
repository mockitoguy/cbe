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
        String flightCode = "LL-123";
        float[] seatPrices = new float[]{1.0f,1.0f};

        manager.addFlight(flightCode, new Flight(seatPrices));
        manager.addFlight("some-othe-flight", new Flight(123, 10));

        // when
        int seats = manager.getAvailableSeats(flightCode);

        // then
        assertEquals(seatPrices.length, seats);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldFailForUnknownFlight() throws Exception {
        // given
        float defaultPrice = 1.0f;
        FlightManager manager = new FlightManager();
        manager.addFlight("AAA", new Flight(defaultPrice, defaultPrice));

        // when
        manager.getFlightInfo("some-flight");

    }

    @Test
    public void shouldFindCheapestSeatInFlight() {
        // given
        float cheapestPrice = 10;
        FlightManager manager = new FlightManager();
        Flight fi = new Flight(cheapestPrice, cheapestPrice+10, cheapestPrice+20);
        Flight fi2 = new Flight(0, 453);
        manager.addFlight("DIY-1020", fi);
        manager.addFlight("DIY-1021", fi2);
        // when
        float price = manager.getCheapestSeat("DIY-1020");
        // then
        assertEquals(cheapestPrice, price);
    }


}
