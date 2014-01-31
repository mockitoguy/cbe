package foo;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class FlightManagerTest {

    private FlightManager manager = new FlightManager();

    @Test
    public void shouldKnowAvailableSeats() {
        // given
        manager.addFlight(Flight.withSeatPrices(1.0f, 2.0f).withNumber("LL-123").build());
        manager.addFlight(Flight.withSeatPrices(123, 10).withNumber("some-other-flight").build());

        // when
        int availableSeats = manager.getAvailableSeats("LL-123");

        // then
        assertThat(availableSeats).isEqualTo(2);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldFailForUnknownFlight() throws Exception {
        manager.getFlightInfo("some-flight");
    }

    @Test(expected = DuplicateFlightException.class)
    public void shouldFailForDuplicateFlight() throws Exception {
        // given
        manager.addFlight(Flight.withNumber("DIY-123").build());

        // when
        manager.addFlight(Flight.withNumber("DIY-123").build());
    }



    @Test
    public void shouldFindCheapestSeatInFlight() {
        // given
        manager.addFlight(Flight.withSeatPrices(10, 20, 30).withNumber("DIY-1020").build());
        manager.addFlight(Flight.withSeatPrices(1, 2).withNumber("DIY-1021").build());

        // when
        float price = manager.getCheapestSeat("DIY-1020");

        // then
        assertEquals((float) 10, price);
    }

    @Test
    public void shouldFindFlightsFromGivenOrigin() {
        // given
        Flight dublinFlight1 = Flight.from("Dublin").withNumber("DIY-1025").build();
        Flight dublinFlight2 = Flight.from("Dublin").withNumber("DIY-1026").build();

        manager.addFlight(dublinFlight1);
        manager.addFlight(dublinFlight2);
        manager.addFlight(Flight.from("Ouagadougou").withNumber("DIY-123").build());

        // when
        List<Flight> flights = manager.getFlightsFrom("Dublin");

        // then
        assertThat(flights).containsOnly(dublinFlight1, dublinFlight2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowAnExceptionWhenFindingNullOrigin() {
        manager.getFlightsFrom(null);
    }

    @Test
    public void shouldFindFlightsToGivenDestination() {
        // given
        Flight dublinFlight1 = Flight.to("Dublin").withNumber("DIY-1025").build();
        Flight dublinFlight2 = Flight.to("Dublin").withNumber("DIY-1026").build();

        manager.addFlight(dublinFlight1);
        manager.addFlight(dublinFlight2);
        manager.addFlight(Flight.to("Ouagadougou").withNumber("DIY-1035").build());

        // when
        List<Flight> flights = manager.getFlightsTo("Dublin");

        // then
        assertThat(flights).containsOnly(dublinFlight1, dublinFlight2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowAnExceptionWhenFindingNullDestination() {
        manager.getFlightsTo(null);
    }

    @Test
    public void shouldFindListBetweenGivenOriginAndDestination() {
        // given
        Flight dublinFlight1 = Flight.from("Dublin").to("Ciechocinek").scheduled(new Date()).withNumber("DIY-1020").build();

        manager.addFlight(dublinFlight1);
        manager.addFlight(Flight.from("Dublin").to("Hannover").scheduled(new Date()).withNumber("DIY-1023").build());
        manager.addFlight(Flight.from("Ouagadougou").to("Honolulu").scheduled(new Date()).withNumber("DIY-123").build());

        // when
        List<Flight> flights = manager.getFlights("Dublin", "Ciechocinek");

        // then
        assertThat(flights).containsOnly(dublinFlight1);
    }
}
