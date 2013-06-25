package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FlightManagerTest {

  private FlightManager flightManager;

  @Before
  public void setUp() {
    flightManager = new FlightManager();
  }

  @Test
  public void shouldReturnNumberOfSeats() throws Exception {
    // given
    String flightNumber1 = "flightNo1";
    String flightNumber2 = "flightNo2";
    int flight1Seats = 5;
    int flight2Seats = 10;

    flightManager.addFlight(buildFlighMocktWithSeats(flightNumber1, flight1Seats));
    flightManager.addFlight(buildFlighMocktWithSeats(flightNumber2, flight2Seats));

    // when
    int actualAvailableSeats1 = flightManager.getAvailableSeats(flightNumber1);
    int actualAvailableSeats2 = flightManager.getAvailableSeats(flightNumber2);

    // then
    assertEquals(flight1Seats, actualAvailableSeats1);
    assertEquals(flight2Seats, actualAvailableSeats2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenAskingForNonexistantFlight() throws Exception {
    // given
    String flightNumber = "flightNo";

    // when
    flightManager.getAvailableSeats(flightNumber);
  }

  private Flight buildFlighMocktWithSeats(String number, int numberOfSeats) {
    Flight flight = Mockito.mock(Flight.class);
    Mockito.when(flight.getNumber()).thenReturn(number);
    Mockito.when(flight.getSeatsCount()).thenReturn(numberOfSeats);
    return flight;
  }

}
