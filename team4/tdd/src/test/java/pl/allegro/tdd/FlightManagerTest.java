package pl.allegro.tdd;

import java.util.List;
import org.fest.assertions.Assertions;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FlightManagerTest {
  
  private FlightManager instance = null;
  
  @Before
  public void beforeTest() {
    instance = new FlightManager();
  }
  
  
  @Test
  public void shouldReturnNumberOfAvailableSeats() {
    // given
    Seat seat1 = new SeatBuilder().withNumber(1).build();
    Seat seat2 = new SeatBuilder().withNumber(2).build();
    
    Flight flight = new FlightBuilder().build("LOT101");
    flight.addSeat(seat1);
    flight.addSeat(seat2);
    
    instance.addFlight(flight);
    
    // when
    int seats = instance.getAvailableSeats("LOT101");
    
    // then
    assertEquals(2, seats);
  }
  
  @Test
  public void shouldReturnSeatWithCheapestPrice() {
    // given
    Flight flight = new FlightBuilder().build("LOT101");
    flight.addSeat(new SeatBuilder().withNumber(1).withPrice(9.99).build());
    flight.addSeat(new SeatBuilder().withNumber(2).withPrice(9.97).build());
    
    instance.addFlight(flight);
    
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
  public void shouldBookSeatOnFlight() throws SeatAlreadyBookedException {
    // given
    Flight exampleflight = new FlightBuilder().build("LOT101");
    exampleflight.addSeat(new SeatBuilder().withNumber(1).build());
    exampleflight.addSeat(new SeatBuilder().withNumber(2).build());
    
    instance.addFlight(exampleflight);
    
    // when
    instance.bookSeat("LOT101", 2);
    
    // then
    Flight flight = instance.getFlight("LOT101");
    
    assertFalse(flight.getSeat(1).isBooked());
    assertTrue(flight.getSeat(2).isBooked());
  }
  
  @Test
  public void shouldFailOnBookSeatThatAlreadyBooked() {
    // given
    Flight exampleflight = new FlightBuilder().build("LOT101");
    exampleflight.addSeat(new SeatBuilder().withNumber(1).booked(true).build());
    
    instance.addFlight(exampleflight);

    try { 
      // when
      instance.bookSeat("LOT101", 1);
      // then
      fail();
    } catch (SeatAlreadyBookedException ex) {}
  }
  
  @Test
  public void shouldReturnAveragePriceOfNonBookedSeatsOnFlight() {
    // given
    Flight exampleflight = new FlightBuilder().build("LOT101");
    
    exampleflight.addSeat(new SeatBuilder().withNumber(1).withPrice(100).build());
    exampleflight.addSeat(new SeatBuilder().withNumber(2).withPrice(100).build());
    exampleflight.addSeat(new SeatBuilder().withNumber(3).booked(true).build());
    
    instance.addFlight(exampleflight);
    
    // when
    double result = instance.getAveragePriceOfNonBookedSeats("LOT101");
    
    // then
    assertEquals(100, result, 0.01);
  }
  
  @Test
  public void shouldReturnListOfFlightsBetweenOriginAndDestination() {
    // given
    instance.addFlight(new FlightBuilder().withOriginAndDestination("Warszawa", "Moskwa").build("WM1"));
    instance.addFlight(new FlightBuilder().withOriginAndDestination("Warszawa", "Moskwa").build("WM2"));
    instance.addFlight(new FlightBuilder().withOriginAndDestination("Warszawa", "Paryż").build("WP1"));
    
    // when
    List<Flight> result = instance.getFlightsBetween("Warszawa", "Moskwa");
    
    // then
    MyAssertions.assertThat(result).containsFlights("WM1", "WM2");
  }
  
  public void shouldReturnListOfFlightsFromOrigin() {
    // given
    Flight expectedFlight = new FlightBuilder().withOrigin("Moskwa").build("MP1");
    
    instance.addFlight(new FlightBuilder().withOrigin("Warszawa").build("WM1"));
    instance.addFlight(new FlightBuilder().withOrigin("Warszawa").build("WM2"));
    instance.addFlight(expectedFlight);
    
    // when 
    List<Flight> result = instance.getFlightsFrom("Moskwa");
    
    // then
    Assertions.assertThat(result).containsOnly(expectedFlight);
  }
  
  public void shouldReturnListOfFlightsToDestination() {
    // given
    Flight expectedFlight = new FlightBuilder().withDestination("Moskwa").build("MM1");
    
    instance.addFlight(new FlightBuilder().withDestination("Kraków").build("WK1"));
    instance.addFlight(new FlightBuilder().withDestination("Warszawa").build("MW2"));
    instance.addFlight(expectedFlight);
    
    // when 
    List<Flight> result = instance.getFlightsTo("Kraków");
    
    // then
    Assertions.assertThat(result).containsOnly(expectedFlight);
  }
}
