package com.ak.flight;

import com.ak.flight.exception.NoSuchFlightExpection;
import com.ak.flight.exception.NoSuchSeatException;
import com.ak.flight.exception.NonFreeSeatsAvailableException;
import com.ak.flight.exception.SeatAlreadyBookedException;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
@Test
public class FlightTest {

  public static final int LOWEST_PRICE = 11000;
  public static final String SEAT_NUMBER = "1A";
  public static final String SEAT_NUMBER1 = "2A";

  public void shouldKnowNumberOfAvailableSeats() throws NoSuchFlightExpection {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().booked().build())
        .addSeat(new SeatBuilder().build())
        .addSeat(new SeatBuilder().build())
        .build();

    //when
    int seatsCount = flight.getAvailableSeatsCount();

    //then
    assertThat(seatsCount).isEqualTo(2);
  }

  public void shouldKnowLowestPrice() {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().price(LOWEST_PRICE).build())
        .addSeat(new SeatBuilder().price(20000).build())
        .build();

    //when
    long lowestPrice = flight.getLowestSeatPrice();

    //then
    assertThat(lowestPrice).isEqualTo(LOWEST_PRICE);
  }

  public void shouldBookSeat() throws SeatAlreadyBookedException, NoSuchSeatException {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().number(SEAT_NUMBER).price(LOWEST_PRICE).build())
        .addSeat(new SeatBuilder().number(SEAT_NUMBER1).price(20000).build())
        .build();

    //when
    flight.bookSeat(SEAT_NUMBER);

    //then
    assertThat(flight.getSeat(SEAT_NUMBER).isBooked()).isTrue();
    assertThat(flight.getSeat(SEAT_NUMBER1).isBooked()).isFalse();
  }

  public void shouldNotBookAlreadyBookedSeat() throws NoSuchSeatException, SeatAlreadyBookedException {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().number(SEAT_NUMBER).build())
        .addSeat(new SeatBuilder().number(SEAT_NUMBER1).build())
        .build();

    flight.bookSeat(SEAT_NUMBER);

    try {
      flight.bookSeat(SEAT_NUMBER);
      fail();
    } catch (SeatAlreadyBookedException e) {
    }
  }

  public void shouldReturnAveragePriceOfFreeSeats() throws NonFreeSeatsAvailableException {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().price(10).build())
        .addSeat(new SeatBuilder().price(20).build())
        .addSeat(new SeatBuilder().price(40).booked().build())
        .build();

    //when
    long averagePrice = flight.getAveragePriceOfFreeSeats();

    //then
    assertThat(averagePrice).isEqualTo(15);
  }

  public void shouldKnowAveragePriceForClass() {
    //given
    Flight flight = new FlightBuilder()
        .addSeat(new SeatBuilder().price(50).flightClass(FlightClass.ECONOMIC).build())
        .addSeat(new SeatBuilder().price(150).flightClass(FlightClass.ECONOMIC).booked().build())
        .addSeat(new SeatBuilder().price(150).flightClass(FlightClass.BUSINESS).build())
        .build();

    //when
    long averagePrice = flight.getAveragePriceForClass(FlightClass.ECONOMIC);

    //then
    assertThat(averagePrice).isEqualTo(100);
  }

  public void shouldKnowSeatsCountWithPriceDifferingFromDefaultForClass() {
    //given
    Flight flight = new FlightBuilder()
        .addDefaultClassPrice(FlightClass.BUSINESS, 15)
        .addDefaultClassPrice(FlightClass.ECONOMIC, 10)
        .addSeat(new SeatBuilder().flightClass(FlightClass.BUSINESS).price(10).build())
        .addSeat(new SeatBuilder().flightClass(FlightClass.BUSINESS).price(15).build())
        .addSeat(new SeatBuilder().flightClass(FlightClass.BUSINESS).price(25).booked().build())
        .addSeat(new SeatBuilder().flightClass(FlightClass.ECONOMIC).price(5).build())
        .build();

    //when
    int seatsCount = flight.getSeatsCountWithoutDefaultClassPrice(FlightClass.BUSINESS);

    //then
    assertThat(seatsCount).isEqualTo(2);
  }

}
