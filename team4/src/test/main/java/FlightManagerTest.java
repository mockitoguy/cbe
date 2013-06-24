import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FlightManagerTest {

  public static final String ID_112 = "id112";

  @Test
  public void shouldTellAvailableSeatsCount() throws IncorrectFlightNumberException {
    // given
    FlightManager flightManager = new FlightManager();
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
    FlightManager flightManager = new FlightManager();

    // when
    int count = 0;
    try {
      count = flightManager.getSeatsCountForFlight("id112");
    } catch (IncorrectFlightNumberException e) {
      assertEquals("Flight doesn't exist", e.getMessage());
    }

    // then
    assertEquals(0, count);
  }

  @Test
  public void shouldKnowTheDefaultPrice() throws IncorrectFlightNumberException {
    //given
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(ID_112, 5);

    //when
    BigDecimal price = flightManager.getCheapestPlacePrice(ID_112);

    //then
    assertEquals(Flight.DEFAULT_PRICE, price);
  }


  @Test
  public void shouldSetPriceOnSeatNumberProvided() throws IncorrectFlightNumberException {
    //given
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(ID_112, 5);

    //when
    flightManager.setSeatPriceForFlight(ID_112, 2, new BigDecimal(12.5));

    //then
    assertEquals(flightManager.getSeatPriceForFlight(ID_112, 2), new BigDecimal(12.5));
  }

  @Test
  public void shouldGetCheapestPrice() throws IncorrectFlightNumberException {
    //given
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(ID_112, 2);
    flightManager.setSeatPriceForFlight(ID_112, 0, new BigDecimal(12.5));

    //when
    BigDecimal cheapestPlacePrice = flightManager.getCheapestPlacePrice(ID_112);

    //then
    assertFalse((new BigDecimal(12.5)).equals(cheapestPlacePrice));
    assertEquals(cheapestPlacePrice, BigDecimal.ONE);
  }


  @Test
  public void shouldUserBookSeatForFlight() throws SeatAlreadyBookedException, IncorrectFlightNumberException {
    //given
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(ID_112, 2);
    User user = new User("Ewa");


    //when
    flightManager.bookPlace(ID_112, 1, user);

    //then
    assertEquals(flightManager.getSeatReservation(ID_112, 1).getName(), user.getName());

  }


}
