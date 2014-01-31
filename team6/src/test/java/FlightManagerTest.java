import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class FlightManagerTest {

    private FlightManager flightManager;

    @Before
    public void setup() {
        flightManager = new FlightManager();

    }

    @Test
    public void shouldKnowAvailableSeats() throws Exception {

        //given
        Flight flight1 = new Flight("LOT-1");
        Flight flight2 = new Flight("LOT-2");
        Seat seatB1 = new Seat("B1", BigDecimal.valueOf(33.55));
        flightManager.addSeat(flight1, seatB1);
        Seat seatB2 = new Seat("B2", BigDecimal.valueOf(45.55));
        flightManager.addSeat(flight1, seatB2);
        Seat seatF5 = new Seat("F5", BigDecimal.valueOf(47.55));
        flightManager.addSeat(flight2, seatF5);
        //when
        int seats = flightManager.getAvailableSeats(flight1);

        //then
        assertThat(seats).isEqualTo(2);

    }

    @Test(expected = FlightNotFoundException.class)
    public void shouldThrowExceptionForNonExistingFlight() throws Exception {

        //given
        Flight flight1 = new Flight("LOT-1");
        Seat seatB1 = new Seat("B1", BigDecimal.valueOf(33.55));
        flightManager.addSeat(flight1, seatB1);

        //when
        flightManager.getAvailableSeats(new Flight("nonExistingFlight"));

        //then
        fail("should throw exception");

    }

    @Test
    public void shouldFindTheCheapestSeat() {
        //given
        Flight flight = new Flight("LOT-1");
        Seat seatB1 = new Seat("B1", BigDecimal.valueOf(33.55));
        flightManager.addSeat(flight, seatB1);
        Seat seatB2 = new Seat("B2", BigDecimal.valueOf(45.55));
        flightManager.addSeat(flight, seatB2);
        //when
        BigDecimal price = flightManager.findCheapestSeatPrice(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(33.55));
    }

    @Test
    public void shouldBookAFlightForAFlight() {

        //given
        Seat seat = new Seat("C6", BigDecimal.TEN);
        Flight flight = new Flight("LOT-123");
        flightManager.addSeat(flight, seat);

        //when
        flightManager.book(seat, flight);

        //then
        assertThat(flightManager.getAvailableSeats(flight)).isEqualTo(0);
    }
}
