import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

public class FlightManagerTest {

    private FlightManager flightManager = new FlightManager();



    @Test
    public void shouldKnowAvailableSeats() throws Exception {

        //given
        Flight flight = new Flight("LOT-1");
        flightManager.addSeats(flight, new SeatBuilder().setNumber("B1").build(), new SeatBuilder().setNumber("B2").setPrice(BigDecimal.ZERO).build());
        flightManager.addSeats(new Flight("LOT-2"), new SeatBuilder().setNumber("F5").build());

        //when
        int seats = flightManager.getAvailableSeats(flight);

        //then
        assertThat(seats).isEqualTo(2);

    }

    @Test
    public void shouldThrowExceptionForNonExistingFlight() throws Exception {

        //given
        flightManager.addSeats(new Flight("LOT-1"), new SeatBuilder().build());

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
                new SeatBuilder().setPrice(BigDecimal.valueOf(10)).build(),
                new SeatBuilder().setPrice(BigDecimal.valueOf(7)).build());

        //when
        BigDecimal price = flightManager.findCheapestSeatPrice(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(7));
    }

    @Test
    public void shouldBookASeatForAFlight() {

        //given
        Flight flight = new Flight("LOT-123");
        Seat seat = new SeatBuilder().setNumber("C5").build();
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

        flightManager.addSeats(flight,
                new SeatBuilder().setPrice(BigDecimal.TEN).build().book(),
                new SeatBuilder().setPrice(BigDecimal.valueOf(10)).build(),
                new SeatBuilder().setPrice(BigDecimal.valueOf(20)).build());

        //when
        BigDecimal price = flightManager.findAveragePriceForNonBookedSeats(flight);

        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(15));
    }

    @Test
    public void shouldReturnFlightForGivenOriginAndDestination() {

        //given
        Date date = new Date();
        Flight flight = new Flight("C3", new Flight.Route("Warsaw", "Dublin"), date);
        flightManager.addSeats(flight, new SeatBuilder().build());
        flightManager.addSeats(new Flight("F2", new Flight.Route("Paris", "London"), date),
                new SeatBuilder().build());

        //when
        List<Flight> flights = flightManager.findFlights("Warsaw", "Dublin");

        //then
        assertThat(flights).contains(flight);
    }
    @Test
    public void shouldReturnFlightForGivenOrigin() {

        //given
        Date date = new Date();
        Flight flight = new Flight("C3", new Flight.Route("Warsaw", "Dublin"), date);
        Flight flight2 = new Flight("C4", new Flight.Route("Warsaw", "Cairo"), date);
        flightManager.addSeats(flight, new SeatBuilder().build());
        flightManager.addSeats(flight2, new SeatBuilder().build());
        flightManager.addSeats(new Flight("F2", new Flight.Route("Paris", "London"), date),
                new SeatBuilder().build());

        //when
        List<Flight> flights = flightManager.findFlightsByOrigin("Warsaw");

        //then
        assertThat(flights).contains(flight, flight2);
    }

    @Test
    public void shouldReturnFlightForGivenDestination() {

        //given
        Date date = new Date();
        Flight flight = new Flight("C3", new Flight.Route("Paris", "Dublin"), date);
        Flight flight2 = new Flight("C5", new Flight.Route("Warsaw", "Dublin"), date);
        flightManager.addSeats(flight, new SeatBuilder().build());
        flightManager.addSeats(flight2, new SeatBuilder().build());
        flightManager.addSeats(new Flight("F2", new Flight.Route("Paris", "London"), date),
                new SeatBuilder().build());

        //when
        List<Flight> flights = flightManager.findFlightsByDestination("Dublin");

        //then
        assertThat(flights).contains(flight, flight2);
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

        public Seat build() {
            return new Seat(number, price);
        }
    }

}
