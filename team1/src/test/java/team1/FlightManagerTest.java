package team1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FlightManagerTest {

	FlightManager flightManager = new SunFlightManager();

	@Before
	public void before() {
		
		FlightBuilder flightBuilder = new FlightBuilder();
		
		flightManager.addFlight(flightBuilder.clean().setId("ID1").
				setOrgin("Warsaw").setDestination("Majami").
				setDate(new Date()).
				addSeat(1, 32.01).
				addSeat(2, 52.99).
				addSeat(3, 12.99).
				setAvailableSeats(10).build());
		flightManager.addFlight(flightBuilder.clean().setId("LG1").
				setOrgin("Poznan").setDestination("Kamczatka").
				setDate(new Date()).
				addSeat(1, 132.99).
				addSeat(2, 432.99).
				addSeat(3, 10.99).setAvailableSeats(12).build());
	}
	
	@Test
	public void shouldReturnAvailableSeats() {
		//given
		//when
		int seatsNo = flightManager.getAvailableSeats("ID1");
		
		//then
		assertEquals(10, seatsNo);
		
	}
	
	@Test
	public void shouldReturnMinusWhenNoFlightSeats() {
		//given
		//when
		int seatsNo = flightManager.getAvailableSeats("ID2");
		
		//then
		assertEquals(-1, seatsNo);
		
	}

	
	
	@Test
	public void shouldReturnCheapestSit() {
		//given
		//when
		double flightPrice = flightManager.getCheapestSeat("ID1");
		//then
		assertEquals(12.99, flightPrice, 0.01);
	}
	
	@Test
	public void shouldBookSeat() {
		//given
		//when
		flightManager.bookSeat("ID1",1);
		boolean availability = flightManager.isSeatAvailable("ID1", 1);
		//then
		assertFalse(availability);
	}
	
	@Test
	public void shouldNotBookUnavailableSeat() {
		//given
		flightManager.bookSeat("ID1",2);
		//when
		boolean result = flightManager.bookSeat("ID1",2);
		//then
		assertFalse(result);
	}
	
	@Test
	public void shouldReturnProperAvailblelAvgPrice() {
		//given
		flightManager.bookSeat("ID1",2);
		//when
		double avg = flightManager.getAveragePrice("ID1", true);
		//then
		assertEquals(22.50, avg, 0.01);
	}
	
	@Test
	public void shouldReturnFlightsBetweenDestinationAndOrgin() {
		//given
		//when
		List<Flight> flights = flightManager.getFlightsBetween("Poznan","Kamczatka");
		//then

		assertEquals(flightManager.getFlight("LG1"),flights.get(0));
	}
	@Test
	public void shouldreturnFlightToDestination() {
		// given
		// when
		List<Flight> flights = flightManager.getFlightsBetween(null,
				"Kamczatka");
		// then
		assertEquals(flightManager.getFlight("LG1"), flights.get(0));
	}
	@Test
	public void shouldReturnFlightFromOrigin() {
		// given
		// when
		List<Flight> flights = flightManager.getFlightsBetween("Warsaw",null);
		// then

		assertEquals(flightManager.getFlight("ID1"), flights.get(0));

	}
}
