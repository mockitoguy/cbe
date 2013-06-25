package tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class FlightAssistantTest {

  private String seat1Number;
  private String seat2Number;
  private BigDecimal seat1Price;
  private BigDecimal seat2Price;

  private Seat seat1;
  private Seat seat2;

  private Flight flight;

  private FlightAssistant flightAssistant;

  @Before
  public void setUp() {
    seat1Number = "seat1";
    seat2Number = "seat2";

    seat1Price = new BigDecimal("15.00");
    seat2Price = new BigDecimal("25.00");

    seat1 = new Seat();
    seat1.setNumber(seat1Number);
    seat1.setPrice(seat1Price);

    seat2 = new Seat();
    seat2.setNumber(seat2Number);
    seat2.setPrice(seat2Price);

    flight = new Flight();
    flight.setSeats(Arrays.asList(seat1, seat2));

    flightAssistant = new FlightAssistant();
  }

  @Test
  public void shouldReturnCheapestSeatPrice() throws Exception {
    // given

    // when
    BigDecimal actualCheapestPrice = flightAssistant.getCheapestPrice(flight);

    // then
    assertEquals(seat1Price, actualCheapestPrice);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnNullWhenCalculatingHeapestPrinceOnFlightWithNoSeats() throws Exception {
    // given
    flight.setSeats(new ArrayList<Seat>());

    // when
    flightAssistant.getCheapestPrice(flight);
  }

  @Test
  public void shouldBookAvailableSeat() throws Exception {
    // given

    // when
    boolean bookingResult = flightAssistant.bookSeat(flight, seat1Number);

    // then
    assertTrue(bookingResult);
    assertTrue(seat1.isBooked());
    assertFalse(seat2.isBooked());
  }

  @Test
  public void shouldNotBookNonAvailableSeat() throws Exception {
    // given
    seat1.setBooked(true);

    // when
    boolean bookingResult = flightAssistant.bookSeat(flight, seat1Number);

    // then
    assertFalse(bookingResult);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenSeatIsNotAvailable() throws Exception {
    // given
    String notExistingSeatNumber = "seatNumber";

    // when
    flightAssistant.bookSeat(flight, notExistingSeatNumber);
  }

  @Test
  public void shouldReturnAverageNonBookedPrice() throws Exception {
    // given

    // when
    BigDecimal averagePrice = flightAssistant.getAverageNonBookedSeatPrice(flight);

    // then
    assertEquals(new BigDecimal("20.00"), averagePrice);
  }

  @Test
  public void shouldNonIncludeBookedSeatsInAverageNonBookedPriceCalculations() throws Exception {
    // given
    seat1.setBooked(true);

    // when
    BigDecimal averagePrice = flightAssistant.getAverageNonBookedSeatPrice(flight);

    // then
    assertEquals(seat2Price, averagePrice);
  }

  @Test
  public void shouldReturnNullWhenAllSeatsAreBooked() throws Exception {
    // given
    seat1.setBooked(true);
    seat2.setBooked(true);

    // when
    BigDecimal averagePrice = flightAssistant.getAverageNonBookedSeatPrice(flight);

    // then
    assertNull(averagePrice);
  }

  @Test
  public void shouldReturnNullWhenCalculatingAveratgeNonBookedSeatPriceOnFlightWithNoSeats() throws Exception {
    // given
    flight.setSeats(new ArrayList<Seat>());

    // when
    BigDecimal averagePrice = flightAssistant.getAverageNonBookedSeatPrice(flight);

    // then
    assertNull(averagePrice);
  }

}
