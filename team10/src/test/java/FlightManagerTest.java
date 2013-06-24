import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightManagerTest {

    private static final String FLIGHT_NAME = "XP001`";

    @Mock
    FlightDAO flightDAO;

    @InjectMocks
    private FlightManager flightManager = new FlightManager(flightDAO);


    @Test
    public void shouldTellAvailableSeatsCount() throws Exception {

        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(
                FlightBuilder.aFlight()
                        .setSeatsCount(4)
                        .build());

        //when
        int count = flightManager.getAvailableSeatsCount(FLIGHT_NAME);

        //then
        assertThat(count).isEqualTo(4);

    }

    @Test
    public void shouldTellPriceOfSeatInGivenFlight() {
        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(
                FlightBuilder.aFlight()
                        .setSeatsCount(2)
                        .setDefaultPrice(100d)
                        .build());

        //when
        double price = flightManager.getSeatPrice(FLIGHT_NAME, 1);

        //then
        assertThat(price).isEqualTo(100d);

    }

    @Test
    public void shouldTellPriceOfCheapestSeatInGivenFlight() {
        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(
                FlightBuilder.aFlight()
                        .setSeatsCount(2)
                        .setDefaultPrice(100d)
                        .build());
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 20d);
        //when
        double price = flightManager.getCheapestSeatPrice(FLIGHT_NAME);

        //then
        assertThat(price).isEqualTo(20d);
    }


    @Test
    public void shouldBookSeatOnGivenFlight() {
        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(new FlightBuilder().setSeatsCount(2).build());

        //when
        flightManager.reserveSeatInFlight(2, FLIGHT_NAME);

        //then
        assertThat(flightManager.isSeatInFlightReserved(2, FLIGHT_NAME)).isTrue();
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnNonBookedFlight() {
        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(new FlightBuilder().setSeatsCount(2).build());
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 20d);
        flightManager.addSeatPrice(FLIGHT_NAME, 2, 40d);
        //when

        Double price = flightManager.getAveragePriceInFlight(FLIGHT_NAME);

        //then
        assertThat(price).isEqualTo(30d);
    }

    @Test
    public void shouldGetAveragePriceOfNonBookedSeatsOnPartialBookedFlight() {
        //given
        when(flightDAO.getFlight(FLIGHT_NAME)).thenReturn(new FlightBuilder().setSeatsCount(3).build());
        flightManager.addSeatPrice(FLIGHT_NAME, 1, 100d);
        flightManager.addSeatPrice(FLIGHT_NAME, 2, 20d);
        flightManager.addSeatPrice(FLIGHT_NAME, 3, 40d);
        flightManager.reserveSeatInFlight(1, FLIGHT_NAME);
        //when


        Double price = flightManager.getAveragePriceInFlight(FLIGHT_NAME);

        //then
        assertThat(price).isEqualTo(30d);
    }

    @Test
    public void shouldGetListOfFlightsBetweenLocations() {
        //given
        Flight flight = new FlightBuilder().build();
        when(flightDAO.findFlightBetween("Warsaw", "Paris")).thenReturn(asList(flight));

        //when
        List<Flight> flightsBetween = flightManager.getFlightsBetween("Warsaw", "Paris");

        //then
        assertThat(flightsBetween).containsOnly(flight);
    }

}
