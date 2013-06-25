import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.flight.Flight;
import net.flight.FlightManager;
import net.flight.FlightRepository;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.sun.istack.internal.Nullable;

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
    flightRepository.addFlightSeats("F01", new SeatBuilder().available().build(),
        new SeatBuilder().available().build(), new SeatBuilder().booked().build());

    flightRepository.addFlightSeats("F03", new SeatBuilder().available().build());

    // when
    int seats = flightManager.getAvailableSeats("F01");

    // then
    assertEquals(2, seats);
  }

  @Test
  public void shouldGetCheapestSeat() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().withPrice("100").build(), new SeatBuilder()
        .withPrice("10").build());

    flightRepository.addFlightSeats("F03", new SeatBuilder().withPrice("5").build());

    // when
    BigDecimal price = flightManager.getCheapestSeatPrice("F01");

    // then
    assertEquals(new BigDecimal("10"), price);
  }

  @Test
  public void shouldBookSeatOnFlight() throws Exception {
    // given
    flightRepository.addFlightSeats("F01", new SeatBuilder().booked().build(), new SeatBuilder().available().build(),
        new SeatBuilder().available().build());

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
    flightRepository.addFlightSeats("F01", new SeatBuilder().withPrice("90").build(), new SeatBuilder().withPrice("10")
        .build());

    // when
    BigDecimal averagePriceNonBookedSeats = flightManager.averagePriceNonBookedSeats("F01");

    // then
    assertEquals(new BigDecimal("50"), averagePriceNonBookedSeats);
  }

  @Test
  public void shouldShowListOfFlightsBetween() throws Exception {
    // given
    Date flightF004Date = new Date();
    flightRepository.addFlights(new FlightBuilder("F004").withOrigin("WAW").withDestination("HAM")
        .withDepartureDate(flightF004Date).build());

    flightRepository.addFlights(new FlightBuilder("F001").withOrigin("WAW2").withDestination("HAM2")
        .withDepartureDate(new Date()).build());

    // when
    List<Flight> flights = flightManager.findFlights("WAW", "HAM");

    // then
    assertThat(flights).isNotNull();
    assertThat(flights).hasSize(1);

    assertThat(flights.get(0).getFlightNo()).isEqualTo("F004");
    assertThat(flights.get(0).getDepartureDate()).isEqualTo(flightF004Date);
  }

  @Test
  public void shouldFindFlightFromOrigin() throws Exception {
    // given
    flightRepository.addFlights(new FlightBuilder("F001").withOrigin("WAW").withDestination("HAM")
        .withDepartureDate(new Date()).build());

    flightRepository.addFlights(new FlightBuilder("F002").withOrigin("WAW").withDestination("HAM2")
        .withDepartureDate(new Date()).build());

    flightRepository.addFlights(new FlightBuilder("F003").withOrigin("HAM").withDestination("WAW")
        .withDepartureDate(new Date()).build());

    // when
    List<Flight> flights = flightManager.findFlightsFrom("WAW");

    // then
    assertThat(flights).isNotNull();
    assertThat(flights).hasSize(2);

    Iterable<String> flightsNo = Iterables.transform(flights, new Function<Flight, String>() {
      @Override
      public String apply(@Nullable Flight flight) {
        if (flight == null) {
          return null;
        }
        return flight.getFlightNo();
      }
    });

    assertThat(flightsNo).contains("F001", "F002");
  }

}
