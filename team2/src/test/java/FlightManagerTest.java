import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    FlightManager flightManager;

    @Before
    public void setup() {
        flightManager = new FlightManager();
    }


    @Test
    public void shouldKnowAvailableSeats() {
        //given
        flightManager.addFlight(new Flight("RA-666", 15));
        flightManager.addFlight(new Flight("LOT-123", 5));

        //when
        int seatsNumber = flightManager.getAvailableSeats("LOT-123");

        //then
        assertEquals(5, seatsNumber);
    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldReturnExceptionIfFlightNoExists() {
        //when
        flightManager.getAvailableSeats("NON-EXISTING-FLIGHT");
    }

    @Test(expected = FlightAlreadyExistsException.class)
    public void shouldThrowFlightAlreadyExistsExceptionWhenTryingToAddExistingFlight() {
        //given
        flightManager.addFlight(new Flight("RA-666", 15));

        //when
        flightManager.addFlight(new Flight("RA-666", 15));
    }

    @Test
    public void shouldGetTheCheapestSeatPriceForAGivenFlight() {
        //given

        Money twoEuro = new Money(BigDecimal.valueOf(2.0), Money.Currency.EUR);
        Money oneEuro = new Money(BigDecimal.valueOf(1.0), Money.Currency.EUR);
        flightManager.addFlight(new Flight("RA-666", 5));
        flightManager.setPrice("RA-666", 1, oneEuro);
        flightManager.setPrice("RA-666", 2 , twoEuro);
        flightManager.setPrice("RA-666", 3 , oneEuro);

        //when
        List<Seat> cheapestSeats = flightManager.getCheapestSeats("RA-666");

        //then
        assertThat(cheapestSeats).isNotEmpty();
        assertThat(cheapestSeats.size()).isEqualTo(2);
        assertThat(cheapestSeats.get(0).getPrice()).isEqualTo(oneEuro);


    }



}
