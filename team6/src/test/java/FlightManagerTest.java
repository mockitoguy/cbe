import org.junit.Test;

import java.math.BigDecimal;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightManagerTest {

    private FlightManager flightManager = new FlightManager();



    @Test
    public void shouldKnowAvailableSeats() throws Exception {

        //given
        Flight flight = new Flight("LOT-1");
        flightManager.addSeats(flight, new SeatBuilder().setNumber("B1").createSeat(), new SeatBuilder().setNumber("B2").setPrice(BigDecimal.ZERO).createSeat());
        flightManager.addSeats(new Flight("LOT-2"), new SeatBuilder().setNumber("F5").createSeat());

        //when
        int seats = flightManager.getAvailableSeats(flight);

        //then
        assertThat(seats).isEqualTo(2);

    }

    @Test
    public void shouldThrowExceptionForNonExistingFlight() throws Exception {

        //given
        flightManager.addSeats(new Flight("LOT-1"), new SeatBuilder().createSeat());

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
                new SeatBuilder().setPrice(BigDecimal.valueOf(10)).createSeat(),
                new SeatBuilder().setPrice(BigDecimal.valueOf(7)).createSeat());

        //when
        BigDecimal price = flightManager.findCheapestSeatPrice(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(7));
    }

    @Test
    public void shouldBookASeatForAFlight() {

        //given
        Flight flight = new Flight("LOT-123");
        Seat seat = new SeatBuilder().setNumber("C5").createSeat();
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
        Seat bookedSeat = mock(Seat.class);
        when(bookedSeat.isBooked()).thenReturn(true);
        flightManager.addSeats(flight,
                bookedSeat,
                new SeatBuilder().setNumber("B2").setPrice(BigDecimal.valueOf(10)).createSeat(),
                new SeatBuilder().setNumber("B3").setPrice(BigDecimal.valueOf(20)).createSeat());

        //when
        BigDecimal price = flightManager.findAveragePriceForNonBookedSeats(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(15));
    }


    private class SeatBuilder {
        private String number;
        private BigDecimal price;

        public SeatBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public SeatBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Seat createSeat() {
            return new Seat(number, price);
        }
    }

}
