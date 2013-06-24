import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 10:40
 */

public class FlightManagerTest {
    @Test
    public void shouldReturnAvailableSeats() {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.setAvailableSeats("E303", 6);


        //when
        int count = flightManager.availableSeats("E303");
        //then

        assertEquals(6, count);
    }

    @Test
    public void shouldReturnTheCheapestSeats() {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.setSeatPrice("E303", 2, new BigDecimal(300));
        flightManager.setSeatPrice("E303", 67, new BigDecimal(120));
        //when
        BigDecimal returnPrice = flightManager.getCheapestSeat("E303");
        //then
        assertEquals(new BigDecimal(120), returnPrice);
    }

    @Test
    public void shouldBookASeat() {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.setSeatPrice("E303", 34, new BigDecimal(456));
        flightManager.setSeatPrice("E303", 56, new BigDecimal(756));
        flightManager.setSeatPrice("E453", 56, new BigDecimal(756));
        //when
        Reservation reservation = flightManager.bookSeat("E303", 56);
        //then
        assertEquals(56, reservation.getSeat());
        assertEquals("E303", reservation.getFlight());
    }

    @Test
    public void shouldReturnAvaragePriceFromNonBookedSeats() {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.setSeatPrice("E404", 01, new BigDecimal(200));
        flightManager.setSeatPrice("E404", 02, new BigDecimal(600));
        flightManager.setSeatPrice("E404", 03, new BigDecimal(666));

        //when
        flightManager.bookSeat("E404", 03);
        BigDecimal returnedValue = flightManager.getAvaragePriceOfNonBookedSeats("E404");

        //then

        assertEquals(new BigDecimal("400.00"), returnedValue);

    }
}
