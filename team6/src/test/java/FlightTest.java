import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.Delta;
import org.fest.assertions.Fail;
import org.junit.Test;

public class FlightTest {
    private static final String FLIGHT_NO = "AA242";

    private static final String SEAT_ONE = "1";

    private static final String SEAT_TWO = "2";

    private static final Long SEAT_ONE_PRICE = 100L;

    private static final Long SEAT_TWO_PRICE = 200L;

    @Test
    public void shouldGetAveragePrice() throws Exception {
        //given
        Flight flight = new FlightBuilder().setFlightNo(FLIGHT_NO)
            .addSeat(new SeatBuilder().free().setSeatNo(SEAT_ONE).setPrice(SEAT_ONE_PRICE).createSeat())
            .addSeat(new SeatBuilder().free().setSeatNo(SEAT_TWO).setPrice(SEAT_TWO_PRICE).createSeat())
            .createFlight();

        //when
        double averagePrice = flight.getAveragePrice();

        //then
        assertThat(averagePrice).isEqualTo(150L, Delta.delta(0.1));
    }

    @Test
    public void shouldFindCheapestSeat() throws Exception {
        //given
        Flight flight = new FlightBuilder().setFlightNo(FLIGHT_NO)
            .addSeat(new SeatBuilder().free().setSeatNo(SEAT_ONE).setPrice(SEAT_ONE_PRICE).createSeat())
            .addSeat(new SeatBuilder().free().setSeatNo(SEAT_TWO).setPrice(SEAT_TWO_PRICE).createSeat())
            .createFlight();

        //when
        long cheapestPrice = flight.getCheapestSeatPrice();

        //then
        assertThat(cheapestPrice).isEqualTo(SEAT_ONE_PRICE);
    }

    @Test
    public void shouldFailIfFlightIsFull() throws Exception {
        //given
        Flight flight = new FlightBuilder()
            .setFlightNo(FLIGHT_NO)
            .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).booked().createSeat())
            .createFlight();

        try {
            //when
            flight.getCheapestSeatPrice();

            //then
            Fail.fail();
        } catch (NoSeatsAvailableException e) {
        }
    }

}
