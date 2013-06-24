package pl.codebyexample;

import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA. User: andrzej.wislowski Date: 24.06.2013 Time:
 * 10:41 To change this template use File | Settings | File Templates.
 */
public class FlightManagerTest {

  @Test
  public void shouldReturnAvailableSeatsForFlight() {
    // given
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(new Flight("F12").addSeat(new SeatBuilder().build()).addSeat(new SeatBuilder().build()));

    flightManager.addFlight(new Flight("F13").addSeat(new SeatBuilder().build()));

    // when
    int seatCount = flightManager.getSeatCountForFlightNumber("F12");

    // then
    Assert.assertEquals(2, seatCount);
  }

  @Test
  public void shouldReturnTheCheapestSeatForFlight() {
    // given
    FlightManager flightManager = new FlightManager();
    Flight flight = new Flight("F12");
    flight.addSeat(new SeatBuilder().withPrice(120).build());
    flight.addSeat(new SeatBuilder().withPrice(140).build());

    flightManager.addFlight(flight);
    flightManager.addFlight(new Flight("F13").addSeat(new SeatBuilder().withPrice(100).build()));

    // when
    BigDecimal cheapestSeatPriceForFlightNumber = flightManager.getTheCheapestSeatPriceForFlightNumber("F12");
    // then
    Assert.assertEquals(new BigDecimal(120), cheapestSeatPriceForFlightNumber);
  }

  @Test
  public void shouldBookAndReturnSeatNumber() throws Exception {
    // given
    FlightManager flightManager = new FlightManager();
    Flight flight = new Flight("F12");
    flight.addSeat(new SeatBuilder().withNumber("1").build());
    flight.addSeat(new SeatBuilder().withNumber("2").build());
    flightManager.addFlight(flight);
    Flight anotherFlight = new Flight("F13").addSeat(new SeatBuilder().withNumber("1").build());
    flightManager.addFlight(anotherFlight);

    // when
    String seatNumber = flightManager.bookSeatForFlightNumber("F12");

    // then
    Assert.assertTrue(flight.containsSeat(seatNumber));
    Assert.assertFalse(flight.isSeatAvailable(seatNumber));
  }

  @Test
  public void shouldReturnAveragePriceForNotBookedSeats() throws Exception {
    // given
    FlightManager flightManager = new FlightManager();
    Flight flight = new Flight("F12");
    flight.addSeat(new SeatBuilder().withPrice(100).build()).addSeat(new SeatBuilder().withPrice(200).build())
        .addSeat(new SeatBuilder().withPrice(400).build());
    flightManager.addFlight(flight);
    flightManager.bookSeatForFlightNumber("F12");

    // when
    BigDecimal avaragePriceOfAvailableSeatsForFlightNumber = flightManager
        .getAvaragePriceOfAvailableSeatsForFlightNumber("F12");
    // then
    Assert.assertEquals(new BigDecimal("300"), avaragePriceOfAvailableSeatsForFlightNumber);
  }
}
