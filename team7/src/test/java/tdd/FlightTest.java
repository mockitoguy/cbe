package tdd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FlightTest {

  private String nonExistingSeatNumber;
  private String seatNumber;
  private Seat seat;
  private Flight flight;

  @Before
  public void setUp() {
    nonExistingSeatNumber = "nonExistingSeatNumber";
    seatNumber = "seatName";

    seat = new Seat();
    seat.setNumber(seatNumber);

    flight = new Flight();
    flight.addSeat(seat);
  }

  @Test
  public void shouldReturnFalseWhenCheckingNonAvailableSeat() throws Exception {
    // given

    // when
    boolean hasSeat = flight.hasSeat(nonExistingSeatNumber);

    // then
    assertFalse(hasSeat);
  }

  @Test
  public void shouldReturnTrueWhenCheckingAvailableSeat() throws Exception {
    // given

    // when
    boolean hasSeat = flight.hasSeat(seatNumber);

    // then
    assertTrue(hasSeat);
  }

  @Test
  public void shouldReturnFalseWhenCheckingBookingOfNonAvailableSeat() throws Exception {
    // given

    // when
    boolean isSeatBooked = flight.isSeatBooked(nonExistingSeatNumber);

    // then
    assertFalse(isSeatBooked);
  }

  @Test
  public void shouldReturnFalseWhenCheckingBookingOfNotBookedSeat() throws Exception {
    // given

    // when
    boolean isSeatBooked = flight.isSeatBooked(seatNumber);

    // then
    assertFalse(isSeatBooked);
  }

  @Test
  public void shouldReturnTrueWhenCheckingBookingOfBookedSeat() throws Exception {
    // given
    seat.setBooked(true);

    // when
    boolean isSeatBooked = flight.isSeatBooked(seatNumber);

    // then
    assertTrue(isSeatBooked);
  }

}
