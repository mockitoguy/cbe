package com.ak.flight;

import com.ak.flight.exception.NoSuchFlightExpection;
import com.ak.flight.exception.NoSuchSeatException;
import com.ak.flight.exception.NonFreeSeatsAvailableException;
import com.ak.flight.exception.SeatAlreadyBookedException;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
@Test
public class FlightManagerTest {

  public static final String FLIGHT_NUMBER = "LOT103";
  public static final int SEATS_COUNT = 5;
  public static final int LOWEST_PRICE = 11000;
  public static final String SEAT_NO = "2A";

  private FlightManager getFlightManager() {
    FlightManager flightManager = new FlightManager();
    flightManager.addFlight(new FlightBuilder().withFlightNumber(FLIGHT_NUMBER)
        .addSeat("1A", LOWEST_PRICE)
        .addSeat(SEAT_NO, 13000)
        .addSeat("3A", 12000)
        .addSeat("4A", 12000)
        .addSeat("5A", 12001)
        .build());
    return flightManager;
  }

  public void shouldKnowNumberOfAvailableSeats() throws NoSuchFlightExpection {
    //given
    FlightManager flightManager = getFlightManager();

    //when
    int seatsCount = flightManager.getAvailableSeatsCount(FLIGHT_NUMBER);

    //then
    assertThat(seatsCount).isEqualTo(SEATS_COUNT);
  }

  public void shouldKnowLowestPrice() {
    //given
    FlightManager flightManager = getFlightManager();
    Flight flight = flightManager.getFlight(FLIGHT_NUMBER);

    //when
    long lowestPrice = flight.getLowestSeatPrice();

    //then
    assertThat(lowestPrice).isEqualTo(LOWEST_PRICE);
  }

  public void shouldBookSeat() throws SeatAlreadyBookedException, NoSuchSeatException {
    //given
    FlightManager flightManager = getFlightManager();
    Flight flight = flightManager.getFlight(FLIGHT_NUMBER);

    //when
    flight.bookSeat(SEAT_NO);
  }

  public void shouldNotBookAlreadyBookedSeat() throws NoSuchSeatException {
    //given
    FlightManager flightManager = getFlightManager();
    Flight flight = flightManager.getFlight(FLIGHT_NUMBER);
    try {
      flight.bookSeat(SEAT_NO);
    } catch (SeatAlreadyBookedException ignored) {
      // cannot happen
    }

    //when
    try {
      flight.bookSeat(SEAT_NO);
    } catch (SeatAlreadyBookedException e) {
      assertThat(e).hasNoCause();
    }
  }

  public void shouldReturnAveragePriceOfFreeSeats() throws NonFreeSeatsAvailableException {
    //given
    FlightManager flightManager = getFlightManager();
    Flight flight = flightManager.getFlight(FLIGHT_NUMBER);

    //when
    long averagePrice = flight.getAveragePriceOfFreeSeats();

    //then
    assertThat(averagePrice).isEqualTo(12000);
  }

}
