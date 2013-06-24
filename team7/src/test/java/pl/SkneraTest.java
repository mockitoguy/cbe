package pl;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SkneraTest {
  Booking booking;

  @Before
  public void init() {
    booking = new Booking();
  }

  @Test
  public void shouldReturnLastSeatPrice() {
    //given
    Seats freeSeats = new Seats();
    freeSeats.forPrice(2000, 1);
    booking.addFlight("F1", freeSeats);
    //when
    int price = booking.findCheapestForFlight("F1");
    //then
    assertThat(2000, is(price));
  }

  @Test
  public void shouldGetCheaperPriceFromTwoSeatsCategory() {
    //given
    Seats freeSeats = new Seats();
    freeSeats.forPrice(2000, 1);
    freeSeats.forPrice(1500, 1);
    booking.addFlight("F1", freeSeats);
    //when
    int price = booking.findCheapestForFlight("F1");
    //then
    assertThat(1500, is(price));
  }

  @Test(expected = NoSeatsException.class)
  public void shouldFail() {
    //given

    //when
    int price = booking.findCheapestForFlight("F1");
    //then
    assertThat(1500, is(price));
  }
}
