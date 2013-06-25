package pl.allegro.tdd;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FlightManagerTest {
  
  private FlightManager instance = null;
  
  @Before
  public void beforeTest() {
    instance = new FlightManager();
    
    Seat seat1 = new Seat();
    seat1.setNumber(1);
    seat1.setPrice(9.99);
    
    Seat seat2 = new Seat();
    seat2.setNumber(2);
    seat2.setPrice(9.97);
    
    Flight flight = new Flight("LOT101");
    flight.addSeat(seat1);
    flight.addSeat(seat2);
    
    instance.addFlight(flight);
  }
  
  
  @Test
  public void shouldReturnNumberOfAvailableSeats() {
    // given
    
    // when
    int seats = instance.getAvailableSeats("LOT101");
    
    // then
    assertEquals(2, seats);
  }
  
  @Test
  public void shouldReturnSeatWithCheapestPrice() {
    // given
    Seat expectedResult = new Seat();
    expectedResult.setNumber(2);
    expectedResult.setPrice(9.96);
    
    instance.addSeatToFlight("LOT101", expectedResult);
    
    // when
    Seat result = instance.getSeatWithCheapestPrice("LOT101");
    
    // then
    assertSame(expectedResult, result);
  }
  
  @Test
  public void shouldBookSeatOnFlight() {
    // when
    instance.bookSeat("LOT101", 2);
    
    // then
    Flight flight = instance.getFlight("LOT101");
    
    assertFalse(flight.getSeat(1).isBooked());
    assertTrue(flight.getSeat(2).isBooked());
  }
  
  @Test
  public void shouldReturnAveragePriceOfNonBookedSeatsOnFlight() {
    // given
    instance.bookSeat("LOT101", 1);
    
    // when
    double result = instance.getAveragePriceOfNonBookedSeats("LOT101");
    
    // then
    assertEquals(9.97, result, 0.01);
  }
}
