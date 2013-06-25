import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.Fail;
import org.junit.Test;

public class FlightManagerTest {
    private static final String FLIGHT_NO = "AA242";

    private static final String FLIGHT_OTHER = "RR242";

    private static final String SEAT_ONE = "1";

    private static final String SEAT_TWO = "2";

    private static final String SEAT_THREE = "3";

    private FlightManager flightManager = new FlightManager();

    @Test
    public void shouldKnowNumberAvailableSeats() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).free().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_TWO).booked().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_THREE).booked().createSeat())
                .createFlight());

        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_OTHER)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).free().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_TWO).free().createSeat())
                .createFlight());

        //when
        int seats = flightManager.getAvailableSeats(FLIGHT_NO);

        //then
        assertThat(seats).isEqualTo(1);
    }

    @Test
    public void shouldThrowNoSuchFlightExceptionWhenFlightDoesntExist() {
        //given

        try {
            //when
            flightManager.getAvailableSeats(FLIGHT_NO);

            //then
            Fail.fail();
        } catch (NoSuchFlightException e) {
        }
    }

    @Test
    public void shouldBookSeatOnFlight() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).free().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_TWO).booked().createSeat())
                .createFlight());

        //when
        Seat seat = flightManager.bookSeat(FLIGHT_NO, SEAT_ONE);

        //then
        assertThat(seat.getSeatNo()).isEqualTo(SEAT_ONE);
    }

    @Test
    public void shouldFailWhenAttemptedToBookAlreadyTakenSeat() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).booked().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_TWO).free().createSeat())
                .createFlight());

        try {
            // when
            flightManager.bookSeat(FLIGHT_NO, SEAT_ONE);

            //then
            Fail.fail();
        } catch (NoSuchSeatAvailableException e) {
        }

    }

    @Test
    public void shouldFailWhenAttemptedToBookNonExistingSeat() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).free().createSeat())
                .createFlight());

        try {
            //when
            flightManager.bookSeat(FLIGHT_NO, SEAT_TWO);

            //then
            Fail.fail();
        } catch (NoSuchSeatAvailableException e) {
        }
    }

    @Test
    public void shouldAvailableSeatsDecrementsAfterBooking() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).free().createSeat())
                .addSeat(new SeatBuilder().setSeatNo(SEAT_TWO).free().createSeat())
                .createFlight());

        int availableSeatsGiven = flightManager.getAvailableSeats(FLIGHT_NO);

        //when
        flightManager.bookSeat(FLIGHT_NO, SEAT_ONE);

        //then
        assertThat(flightManager.getAvailableSeats(FLIGHT_NO)).isEqualTo(availableSeatsGiven - 1);
    }

    @Test
    public void shouldNotBookSingleSeatOnFullFlight() throws Exception {
        //given
        flightManager.addFlight(
            new FlightBuilder().setFlightNo(FLIGHT_NO)
                .addSeat(new SeatBuilder().setSeatNo(SEAT_ONE).booked().createSeat())
                .createFlight());

        try {
            //when
            flightManager.bookSeat(FLIGHT_NO, SEAT_ONE);

            //then
            Fail.fail();
        } catch (NoSeatsAvailableException e) {
        }
    }

}
