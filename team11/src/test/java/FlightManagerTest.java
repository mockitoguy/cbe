import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 10:40
 */

public class FlightManagerTest {

    private FlightManager flightManager = new FlightManager();

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
        flightManager.setSeatPrice("E404", 01, new BigDecimal(200));
        flightManager.setSeatPrice("E404", 02, new BigDecimal(600));
        flightManager.setSeatPrice("E404", 03, new BigDecimal(666));

        //when
        flightManager.bookSeat("E404", 03);
        BigDecimal returnedValue = flightManager.getAvaragePriceOfNonBookedSeats("E404");

        //then

        assertEquals(new BigDecimal("400.00"), returnedValue);

    }

    @Test
    public void shouldGetListOfFlights() {
        //given
        Flight expected = builder().withDestination("Warsaw").withOrigin("Poznan").build();
        flightManager.setFlight(expected, builder().withDestination("Poznan").withOrigin("Konin").build(), builder().withDestination("Warsaw").withOrigin("Radom").build());
        //when
        List<Flight> returnedFlights = flightManager.getAvalibleFlights("Poznan", "Warsaw");
        //then
        assertThat(returnedFlights).containsOnly(expected);

    }


    @Test
    public void shouldNotReturnAnyFlights() {
        //given
        flightManager.setFlight(builder().withDestination("Warsaw").withOrigin("Poznan").build());
        flightManager.setFlight(builder().withDestination("Poznan").withOrigin("Bialystok").build());
        //when
        List<Flight> returnedFlights = flightManager.getAvalibleFlights("Bialystok", "Wroclaw");
        //then
        assertThat(returnedFlights).isEmpty();

    }

    @Test
    public void shouldFindByOrigin() {
        //given
        Flight expected = builder().withDestination("Warsaw").withOrigin("Poznan").build();
        Flight expected_1 = builder().withDestination("Konin").withOrigin("Poznan").build();
        Flight expected_2 = builder().withDestination("Warsaw").withOrigin("Poznan").build();
        flightManager.setFlight(expected, expected_1, expected_2, builder().withDestination("Warsaw").withOrigin("Radom").build());
        //when
        List<Flight> flightList = flightManager.getAllFlightsFromOrigin("Poznan");

        //then
        assertThat(flightList).containsOnly(expected, expected_1, expected_2);

    }

    @Test
    public void shouldFindByDestination() {
        //given
        Flight expected = builder().withDestination("Warsaw").withOrigin("Poznan").build();
        Flight expected_1 = builder().withDestination("Warsaw").withOrigin("Poznan").build();
        flightManager.setFlight(expected, expected_1,
                builder().withNumber("E405").withDestination("Konin").withOrigin("Radom").build());
        //when
        List<Flight> flightList = flightManager.getAllFlightsToDestination("Warsaw");

        //then
        assertThat(flightList).containsOnly(expected, expected_1);
    }

    private FlightBuilder builder() {
        return new FlightBuilder();
    }
}



