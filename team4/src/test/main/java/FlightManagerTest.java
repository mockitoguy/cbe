import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

public class FlightManagerTest {

  public static final String ID_112 = "id112";
  private FlightManager flightManager = new FlightManager();

  @Test
  public void shouldTellAvailableSeatsCount() throws IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 5);
    flightManager.addFlight("id12", 15);

    // when
    int count = flightManager.getSeatsCountForFlight(ID_112);

    // then
    assertEquals(5, count);
  }

  @Test
  public void shouldThrowIncorrectFlightNumberExceptionWhenFlightNotExists() {
    // given

    // when
    try {
      flightManager.getSeatsCountForFlight("id112");
      fail();
    } catch (IncorrectFlightNumberException e) {
      assertEquals("Flight doesn't exist", e.getMessage());
    }
  }

  @Test
  public void shouldKnowTheDefaultPrice() throws IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 5);

    // when
    BigDecimal price = flightManager.getCheapestPlacePrice(ID_112);

    // then
    assertEquals(Flight.DEFAULT_PRICE, price);
  }

  @Test
  public void shouldSetPriceOnSeatNumberProvided() throws IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 5);

    // when
    flightManager.setSeatPriceForFlight(ID_112, 2, new BigDecimal(12.5));

    // then
    assertEquals(flightManager.getSeatPriceForFlight(ID_112, 2), new BigDecimal(12.5));
  }

  @Test
  public void shouldGetCheapestPrice() throws IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 2);
    flightManager.setSeatPriceForFlight(ID_112, 0, new BigDecimal(12.5));

    // when
    BigDecimal cheapestPlacePrice = flightManager.getCheapestPlacePrice(ID_112);

    // then
    assertFalse((new BigDecimal(12.5)).equals(cheapestPlacePrice));
    assertEquals(cheapestPlacePrice, BigDecimal.ONE);
  }

  @Test
  public void shouldUserBookSeatForFlight() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 2);
    User user = new User("Ewa");

    // when
    flightManager.bookPlace(ID_112, 1, user);

    // then
    assertEquals(flightManager.getSeatReservation(ID_112, 1).getName(), user.getName());

  }

  @Test
  public void shouldGetAveragePrice() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    // given
    flightManager.addFlight(ID_112, 3);
    User user = new User("Ewa");
    flightManager.setSeatPriceForFlight(ID_112, 0, new BigDecimal(12.5));
    flightManager.setSeatPriceForFlight(ID_112, 1, new BigDecimal(3.5));
    flightManager.bookPlace(ID_112, 2, user);

    // when
    BigDecimal averagePrice = flightManager.getAveragePrice(ID_112);

    // then
    assertTrue(averagePrice.compareTo(new BigDecimal(8.0)) == 0);

  }

  @Test
  public void shouldFlightsForGivenCriteria() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    // given
    Date date = new Date();

    Flight f1 = new FlightBuilder().flightNo("id1").from("Warszawa").to("Gdansk").on(date).build();
    flightManager.addFlight(f1);
    flightManager.addFlight(new FlightBuilder().flightNo("id2").from("Warszawa").to("Wroclaw").on(date).build());
    flightManager.addFlight(new FlightBuilder().flightNo("id3").from("Warszawa").to("Gdansk").on(new Date()).build());

    // when
    List<Flight> flights = flightManager.findFlightsFromToOn("Warszawa", "Gdansk", date);

    // then
    assertThat(flights).hasSize(1).containsExactly(f1);

  }

  @Test
  public void shouldFlightsForGivenOrigin() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    // given
    flightManager.addFlight(new FlightBuilder().flightNo("id1").from("Warszawa").build());
    flightManager.addFlight(new FlightBuilder().flightNo("id2").from("Warszawa").build());
    flightManager.addFlight(new FlightBuilder().flightNo("id3").from("Wroclaw").build());

    // when
    List<Flight> flights = flightManager.findFlightsFrom("Warszawa");

    // then
    assertThat(flights).hasSize(2);

  }

  @Test
  public void shouldFlightsForGivenDestination() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    // given
    flightManager.addFlight(new FlightBuilder().flightNo("id1").to("Warszawa").build());
    flightManager.addFlight(new FlightBuilder().flightNo("id2").to("Warszawa").build());
    flightManager.addFlight(new FlightBuilder().flightNo("id3").to("Wroclaw").build());

    // when
    List<Flight> flights = flightManager.findFlightsTo("Warszawa");

    // then
    assertThat(flights).hasSize(2);

  }

  @Test
  public void shouldGetAveragePriceOfSeatsOfClass() {
    // given

    Seat businessSeat1 = new SeatBuilder().price(Flight.DEFAULT_PRICE).seatClass(SeatClass.BUSINESS).build();
    Seat businessSeat2 = new SeatBuilder().price(new BigDecimal(3)).seatClass(SeatClass.BUSINESS).build();
    Seat economicSeat1 = new SeatBuilder().price(new BigDecimal(3)).seatClass(SeatClass.ECONOMIC).build();

    flightManager.addFlight(new FlightBuilder().flightNo("id1").to("Warszawa").addSeat(businessSeat1)
        .addSeat(businessSeat2).addSeat(economicSeat1).build());

    // when
    BigDecimal price = flightManager.averagePriceInClass("id1", SeatClass.BUSINESS);

    // then
    assertThat(price).isEqualTo(new BigDecimal(2));

  }

}
