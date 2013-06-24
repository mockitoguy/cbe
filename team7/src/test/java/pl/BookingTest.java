package pl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static pl.Seats.seats;

public class BookingTest {
  @Test
  public void shouldGetAvailableSeats() {
    //given
    Booking booking = new Booking();
    booking.addFlight("DUPA_FLIGHT", seats().forPrice(1, 40));
    //when
    int seatsNo = booking.findFreeSeatsForFlight("DUPA_FLIGHT");
    //then
    assertThat(40, is(seatsNo));
  }

  @Test
  public void shouldFindNoSeatsForUnknownFlight() {
    //given
    Booking booking = new Booking();
    //when
    int seatsNo = booking.findFreeSeatsForFlight("DUPA_FLIGHT");
    //then
    assertThat(0, is(seatsNo));
  }

  @Test
  public void shouldFindNoSeatsFor2Flights() {
    //given
    Booking booking = new Booking();
    booking.addFlight("DUPA_FLIGHT", seats().forPrice(1,40));
    booking.addFlight("DUPA_FLIGHT2", seats().forPrice(1,30));
    //when
    int seatsOfFirstFlightNo = booking.findFreeSeatsForFlight("DUPA_FLIGHT");
    int seatsOfSecondFlightNo = booking.findFreeSeatsForFlight("DUPA_FLIGHT2");
    //then
    assertThat(40, is(seatsOfFirstFlightNo));
    assertThat(30, is(seatsOfSecondFlightNo));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldHitYouInThaFaceForNegativeSeatsNumber(){
    //given
    Booking booking = new Booking();
    booking.addFlight("DUPA_FLIGHT", seats().forPrice(1,-1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotLetYouAddFlightWithEmptyString(){
    //given
    Booking booking = new Booking();
    booking.addFlight("   ", seats().forPrice(0,0));
  }
}
