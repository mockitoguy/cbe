import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.fest.assertions.Delta;
import org.junit.Test;

/**
 * @author: pgrela
 */
public class FlightTest {
    @Test
    public void shouldReturnCheapestSeatPrice() {
        //given
        Flight flight = Flight.createBuilder().withDefaultPrice(100).withSeatsQuantity(5).build();
        flight.setSeatPrice(2, 200);
        flight.setSeatPrice(4, 20);

        //when
        int cheapestSeatPrice = flight.getCheapestSeatPrice();

        //then
        assertEquals(20, cheapestSeatPrice);
    }

    @Test
    public void shouldBookASeat() {
        //given
        Flight flight = Flight.createBuilder().withSeatsQuantity(5).build();

        //when
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(3);
        assertEquals(true, seat.isBooked());
    }

    @Test
    public void shouldNotBookASeat() {
        //given
        Flight flight = Flight.createBuilder().withSeatsQuantity(5).build();

        //when
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(4);
        assertEquals(false, seat.isBooked());
    }

    @Test
    public void shouldReturnNotBookedSeatsAveragePrice() {
        //given
        Flight flight = Flight.createBuilder().withSeatsQuantity(10).withDefaultPrice(20).build();
        flight.setSeatPrice(3, 200);
        flight.setSeatPrice(1, 11);
        flight.bookSeat(3);

        //when
        double average = flight.getNotBookedSeatsAveragePrice();

        //then
        assertEquals(19, average, 0.0001);
    }


    @Test
    public void shouldReturnAveragePriceInAGivenClass() {
        //given
        Flight flight = Flight.createBuilder()
            .withDefaultPrice(20)
            .withSeatsQuantity(10)
            .withDefaultPrice(10, Seat.TRAVEL_CLASS.BUSSINESS)
            .setSeatsClass(Seat.TRAVEL_CLASS.BUSSINESS, 6, 7, 8, 9, 10)
            .build();
        flight.setSeatPrice(6, 15);

        //when
        double average = flight.getSeatsAveragePrice(Seat.TRAVEL_CLASS.BUSSINESS);

        //then
        assertThat(average).isEqualTo(11.0, Delta.delta(0.0001));
    }

    @Test
    public void shouldReturnNumberOfSeatsWithPriceDifferentThatDefaultInAGivenClass() {
        //given
        Flight flight = Flight.createBuilder()
            .withDefaultPrice(10, Seat.TRAVEL_CLASS.ECONOMIC)
            .withDefaultPrice(50, Seat.TRAVEL_CLASS.BUSSINESS)
            .withSeatsQuantity(10)
            .setSeatsClass(Seat.TRAVEL_CLASS.BUSSINESS, 1,2,3)
            .build();
        flight.getSeat(2).setPrice(15);

        //when
        int quantity = flight.getNumberOfSeatsWithDifferentThanDefaultPrice(Seat.TRAVEL_CLASS.BUSSINESS);

        //then
        assertThat(quantity).isEqualTo(1);
    }
}
