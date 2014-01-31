import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static java.math.BigDecimal.valueOf;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:26
 */
public class FlightManagerTest {

    FlightManager manager = new FlightManager();

    @Test
    public void shouldUserKnowNumberOfAvailableSeatsForFlight() throws Exception {
        //given
        manager.addFlight(new FlightBuilder().setNumber("LOT-123").addNumberOfSeats(2).build());
        manager.addFlight(new FlightBuilder().setNumber("LOT-456").addNumberOfSeats(3).build());

        //when
        int seats = manager.getAvailableSeatsForFlight("LOT-123");

        //then
        assertThat(seats).isEqualTo(2);
    }

    @Test
    public void shouldThrowExceptionWhenFlightNotExists() throws Exception {
        // given
        manager.addFlight(new FlightBuilder().build());

        // when
        when(manager).getAvailableSeatsForFlight("notExistingFlightNumber");

        // then
        then(caughtException()).isExactlyInstanceOf(NoFlightFoundException.class);
    }

    @Test
    public void shouldUserGetCheapestSeatForGivenFlight() throws Exception {
        // given
        Flight flight = new FlightBuilder().setNumber("LOT-123")
                .addSeatWithPrice(valueOf(1))
                .addSeatWithPrice(valueOf(2))
                .addSeatWithPrice(valueOf(3))
                .build();
        manager.addFlight(flight);

        // when
        BigDecimal price = manager.getCheapestSeatForFlight("LOT-123");

        // then
        assertThat(price).isEqualTo(valueOf(1));
    }

    @Test
    public void shouldUserBookSeatForFlight() throws Exception {
        //given
        String flightNumber = "LOT-333";
        manager.addFlight(new FlightBuilder()
                .setNumber(flightNumber)
                .addSeats("1", "2", "3")
                .build());

        //when
        manager.bookSeat(flightNumber, "3");

        //then
        assertThat(manager.getAvailableSeatsForFlight(flightNumber)).isEqualTo(2);
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeats() throws Exception {
        // given
        manager.addFlight(new FlightBuilder().setNumber("LOT-123")
                .addSeatWithPrice(valueOf(10))
                .addSeatWithPrice(valueOf(20))
                .addSeatWithPrice(valueOf(30))
                .build());

        // when
        BigDecimal price = manager.getAveragePriceOfNonBookedSeats("LOT-123");

        // then
        assertThat(price).isEqualByComparingTo(valueOf(20));
    }

    @Test
    public void shouldKnowNumberOfFlighsBetweenOriginAndDestination() throws Exception {
        //given
        Flight flight = new FlightBuilder()
                .setNumber("LOT-123")
                .setOrigin("Warsaw")
                .setDestination("London")
                .setDate("2014-01-31")
                .build();
        manager.addFlight(flight);
        manager.addFlight(new FlightBuilder()
                .setNumber("LOT-456")
                .setOrigin("Warsaw")
                .setDestination("New York")
                .setDate("2014-02-15")
                .build());

        //when
        List<Flight> flights = manager.getNumberOfFlights("Warsaw", "London");

        //then
        assertThat(flights).containsExactly(flight);
    }

    @Test
    public void shouldKnowNumberOfFlightsFromOrigin() throws Exception {
        // given
        Flight flightFromWarsaw = new FlightBuilder()
                .setNumber("LOT-123")
                .setOrigin("Warsaw")
                .build();
        manager.addFlight(flightFromWarsaw);
        manager.addFlight(new FlightBuilder()
                .setNumber("LOT-456")
                .setOrigin("London")
                .build());

        // when
        List<Flight> flights = manager.getNumberOfFlightsForOrigin("Warsaw");

        // then
        assertThat(flights).containsExactly(flightFromWarsaw);
    }

    @Test
    public void shouldKnowNumberOfFlightsToDestination() throws Exception {
        // given
        Flight flightToTokio = new FlightBuilder()
                .setNumber("LOT-123")
                .setDestination("Tokio")
                .build();
        manager.addFlight(flightToTokio);
        Flight flightToPekin = new FlightBuilder()
                .setNumber("LOT-456")
                .setDestination("Pekin")
                .build();
        manager.addFlight(flightToPekin);

        // when
        List<Flight> flights = manager.getNumberOfFlightsToDestination("Pekin");

        // then
        assertThat(flights).containsExactly(flightToPekin);
    }
}
