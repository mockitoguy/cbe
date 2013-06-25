import net.flight.FlightManager;
import net.flight.FlightRepository;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FlightManagerTest {

  private FlightManager flightManager;
  private FlightRepository flightRepository;

  @Before
  public void setUp() throws Exception {
    flightManager = new FlightManager();

    flightRepository = new FlightRepository();

    ArrayList<FlightRepository.Seat> seats = new ArrayList<FlightRepository.Seat>();
    seats.add(new FlightRepository.Seat("120.0"));
    seats.add(new FlightRepository.Seat("50.0"));
    seats.add(new FlightRepository.Seat("10.0"));
    flightRepository.addFlightPrices("F01", seats);

    seats = new ArrayList<FlightRepository.Seat>();
    seats.add(new FlightRepository.Seat("120.0"));
    seats.add(new FlightRepository.Seat("150.0"));
    seats.add(new FlightRepository.Seat("10.0"));
    seats.add(new FlightRepository.Seat("15.0"));
    seats.add(new FlightRepository.Seat("120.0"));
    flightRepository.addFlightPrices("F03", seats);

    flightManager.setFlightRepository(flightRepository);
  }

  @Test
  @Parameters({ "3, F01", "5, F03" })
  public void shouldGetAvailableSeats(int expectedSeats, String flightNo) throws Exception {
    // given

    // when
    int seats = flightManager.getAvailableSeats(flightNo);

    // then
    assertEquals(expectedSeats, seats);
  }

  @Test
  public void shouldGetCheapestSeat() throws Exception {
    // given

    // when
    BigDecimal price = flightManager.getCheapestSeatPrice("F01");

    // then
    assertEquals(new BigDecimal("10.0"), price);
  }

  @Test
  public void shouldBookSeatOnFlight() throws Exception {
    // given
    int availableSeatsBeforeBooking = flightManager.getAvailableSeats("F01");

    // when
    flightManager.bookSeatOnFlight("F01");

    // then
    assertEquals(availableSeatsBeforeBooking - 1, flightManager.getAvailableSeats("F01"));

  }

  @Test
  public void shouldGetAveragePriceOfNonBookedSeats() throws Exception {
    // given

    // when
    BigDecimal averagePriceNonBookedSeats = flightManager.averagePriceNonBookedSeats("F01");

    // then
    assertEquals(new BigDecimal("60.0"), averagePriceNonBookedSeats);
  }

  @Test
  public void shouldShowListOfFlightsBetween() throws Exception {
    //given

    //when

    //then

  }
}
