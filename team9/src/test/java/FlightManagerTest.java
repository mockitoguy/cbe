import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import net.flight.FlightManager;
import net.flight.FlightRepository;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FlightManagerTest {

  private FlightManager flightManager;
  private FlightRepository flightRepository;

  @Before
  public void setUp() throws Exception {
    flightManager = new FlightManager();

    flightRepository = new FlightRepository();
    flightManager.setFlightRepository(flightRepository);
  }

  @Test
  public void shouldGetAvailableSeats() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().available().build(), new SeatBuilder().available()
            .build(), new SeatBuilder().booked().build());

    flightRepository.addFlightSeats("F03", new SeatBuilder().available().build());

    // when
    int seats = flightManager.getAvailableSeats("F01");

    // then
    assertEquals(2, seats);
  }

  @Test
  public void shouldGetCheapestSeat() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().withPrice("100").build(),
            new SeatBuilder().withPrice("10").build());

    flightRepository.addFlightSeats("F03", new SeatBuilder().withPrice("5").build());

    // when
    BigDecimal price = flightManager.getCheapestSeatPrice("F01");

    // then
    assertEquals(new BigDecimal("10"), price);
  }

  @Test
  public void shouldBookSeatOnFlight() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().booked().build(), new SeatBuilder().available()
            .build(), new SeatBuilder().available().build());

    int availableSeatsBeforeBooking = flightManager.getAvailableSeats("F01");

    // when
    boolean success = flightManager.bookSeatOnFlight("F01");

    // then
    assertTrue(success);
    assertEquals(availableSeatsBeforeBooking - 1, flightManager.getAvailableSeats("F01"));
  }

  @Test
  public void shouldGetAveragePriceOfNonBookedSeats() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().withPrice("90").build(),
            new SeatBuilder().withPrice("10").build());

    // when
    BigDecimal averagePriceNonBookedSeats = flightManager.averagePriceNonBookedSeats("F01");

    // then
    assertEquals(new BigDecimal("50"), averagePriceNonBookedSeats);
  }

  @Test
  public void shouldShowListOfFlightsBetween() throws Exception {
    // given

    // when

    // then

  }
}
