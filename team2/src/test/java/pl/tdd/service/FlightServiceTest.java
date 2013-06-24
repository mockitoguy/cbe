package pl.tdd.service;

import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static pl.tdd.service.FlightTestBuilder.*;


/**
 * User: pcierpiatka
 */
public class FlightServiceTest {

    private FlightService flightService;

    @Before
    public void init() {
        flightService = new FlightService();
    }

    @Test
    public void shouldTellAvailableSeatsCount() {
        //given
        flightService.addFlight(flight("LA101").withSeats(5).build());
        flightService.addFlight(flight("AE500").withSeats(15).build());

        //when
        int availableSeatsCount = flightService.getAvailaleSeatsCount("LA101");

        //then
        assertThat(availableSeatsCount).isEqualTo(5);
    }

    @Test
    public void shouldThrowUnknownFlightWhenFlightDoesntExists() {
        //given

        //when
        catchException(flightService).getAvailaleSeatsCount("GHOST_FLIGHT");

        //then
        assertThat(caughtException()).isInstanceOf(UnknownFlightException.class);
    }


    @Test
    public void shouldReturnPriceOfACheapestSeatInGivenFlight() {
        // given
        double cheapestSeatPrice = 10;

        Flight testFlight = flight("L101")
                .withSeatsInPrice(20, 100)
                .withSeatsInPrice(10, 32)
                .withSeatsInPrice(1, cheapestSeatPrice).build();
        flightService.addFlight(testFlight);

        // when
        double cheapestFlightSeat = flightService.getPriceOfCheapestFlightSeat("L101");

        // then
        assertThat(cheapestFlightSeat).isEqualTo(cheapestFlightSeat);
    }

    @Test
    public void shouldBookedSeatForUserInGivenFlight() {
        //given
        Flight testFlight = flight("L102").withSeats(20).withSeat("12A").build();
        flightService.addFlight(testFlight);
        //when
        flightService.book("L102", "12A");

        //then
        assertThat(flightService.isFlightSeatAvailable("L102", "12A")).isFalse();
    }

    @Test
    public void shouldThrowUnknownSeatExceptionIfSeatDoesNotExist() {
        // given
        Flight flight = flight("L102").build();
        flightService.addFlight(flight);

        //when
        catchException(flightService).book("L102", "GHOST_SEAT");

        // then
        assertThat(caughtException()).isInstanceOf(UnknownSeatException.class);
    }

    @Test
    public void shouldThrowSeatAlreadyBookedExceptionIfSeatAlreadyBooked() {
        // given
        String bookedSeatCode = "BOOKED_SEAT";
        Flight flight = flight("L102").withBookeSeat(bookedSeatCode).build();
        flightService.addFlight(flight);

        //when
        catchException(flightService).book("L102", bookedSeatCode);

        // then
        assertThat(caughtException()).isInstanceOf(SeatAlreadyBookedException.class);

    }

    @Test
    public void shouldReturnAvgPriceOfAvailableSeatsInFlight() {
        // given
        Flight testFlight = flight("L102").withSeatsInPrice(100, 10).withSeatsInPrice(100, 20).
                withBookedSeatsInPrice(100, 10).build();
        flightService.addFlight(testFlight);

        // when
        double avgPrice = flightService.getAveragePriceOfAvailableSeats("L102");

        // then
        assertThat(avgPrice).isEqualTo(15);
    }
}
