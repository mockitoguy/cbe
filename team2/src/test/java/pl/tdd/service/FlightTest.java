package pl.tdd.service;

import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static pl.tdd.service.FlightTestBuilder.flight;

/**
 * User: pcierpiatka
 */
public class FlightTest {

    @Test
    public void shouldReturnPriceOfCheapestSeat() {
        //given
        double cheapestPrice = 20,
               expensivePrice = 100;
        Flight flight = flight("TEST_FLIGHT")
                .withSeatsInPrice(100, expensivePrice)
                .withSeatsInPrice(1, cheapestPrice).build();

        //when
        double price = flight.getCheapestSeatPrice();

        //then
        assertThat(price).isEqualTo(cheapestPrice);
    }

    @Test
    public void shouldBookSeatWithGivenNumber() {
        // given
        String bookedSeatCode = "TEST_SEAT";
        Flight flight = flight("TEST_FLIGHT").withSeats(20).withSeat(bookedSeatCode).build();

        // when
        flight.bookSeat(bookedSeatCode);

        // then
        assertThat(flight.isSeatAvailable(bookedSeatCode)).isFalse();
    }

    @Test
    public void shouldReturnAveragePriceOfAvailableSeats() {
        //given
        Flight flight = flight("TEST_FLIGHT").withSeatsInPrice(100, 10).withBookedSeatsInPrice(100, 20).build();

        //when
        double averagePriceOfAvailableSeats = flight.getAveragePriceOfAvailableSeats();

        //then
        assertThat(averagePriceOfAvailableSeats).isEqualTo(10);

    }

    @Test
    public void shouldThrowUnknownSeatException() {

        //given
        Flight flight = flight("TEST_FLIGHT").withSeatsInPrice(100, 10).withBookedSeatsInPrice(100, 20).build();

        //when
        catchException(flight).bookSeat("TEST_SEAT_NAME");

        //then
        assertThat(caughtException()).isInstanceOf(UnknownSeatException.class);
    }

    @Test
    public void shouldThrowSeatAlreadyBookedException() {

        //given
        String seatName = "TEST_SEAT_NAME";
        Flight flight = flight("TEST_FLIGHT").withBookeSeat(seatName).build();

        //when
        catchException(flight).bookSeat(seatName);

        //then
        assertThat(caughtException()).isInstanceOf(SeatAlreadyBookedException.class);
    }

}
