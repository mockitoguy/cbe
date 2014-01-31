import org.junit.Test;

import java.math.BigDecimal;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightManagerTest {

    private FlightManager flightManager = new FlightManager();



    @Test
    public void shouldKnowAvailableSeats() throws Exception {

        //given
        Flight flight = new Flight("LOT-1");
        flightManager.addSeats(flight, new Seat("B1", BigDecimal.TEN), new Seat("B2", BigDecimal.ZERO));
        flightManager.addSeats(new Flight("LOT-2"), new Seat("F5", BigDecimal.ONE));

        //when
        int seats = flightManager.getAvailableSeats(flight);

        //then
        assertThat(seats).isEqualTo(2);

    }

    @Test
    public void shouldThrowExceptionForNonExistingFlight() throws Exception {

        //given
        flightManager.addSeats(new Flight("LOT-1"), new Seat("B1", BigDecimal.TEN));

        //when
        catchException(flightManager).getAvailableSeats(new Flight("nonExistingFlight"));

        //then
        assertThat(caughtException()).isInstanceOf(FlightNotFoundException.class);

    }

    @Test
    public void shouldFindTheCheapestSeat() {

        //given
        Flight flight = new Flight("LOT-1");
        flightManager.addSeats(flight,
                new Seat("B1", BigDecimal.valueOf(10)),
                new Seat("B2", BigDecimal.valueOf(7)));

        //when
        BigDecimal price = flightManager.findCheapestSeatPrice(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(7));
    }

    @Test
    public void shouldBookAFlightForAFlight() {

        //given
        Flight flight = new Flight("LOT-123");
        Seat seat = new Seat("C6", BigDecimal.TEN);
        flightManager.addSeats(flight, seat);

        //when
        flightManager.book(seat, flight);

        //then
        assertThat(flightManager.getAvailableSeats(flight)).isEqualTo(0);
    }
    @Test
    public void shouldFindTheAverageSeatPriceForNonBookedSeats() {

        //given
        Flight flight = new Flight("LOT-1");
        Seat seat1 = new Seat("B1", BigDecimal.valueOf(10));
        flightManager.addSeats(flight,
                seat1,
                new Seat("B2", BigDecimal.valueOf(18)),
                new Seat("B3", BigDecimal.valueOf(20)));

        flightManager.book(seat1, flight);
        //when
        BigDecimal price = flightManager.findAveragePriceForNonBookedSeats(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(19));
    }

}
