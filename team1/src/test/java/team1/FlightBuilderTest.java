package team1;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightBuilderTest {

	@Test
	public void shouldCreateProperFlight() {
		//given
		FlightBuilder flightBuilder = new FlightBuilder();
		Flight flight = flightBuilder.clean().setAvailableSeats(10).setId("AHA1").build();
		
		//when
//		int availableSeats = flight.getAvailableSeats();
		String id = flight.getId();
		
		//then
		assertEquals("AHA1", id);
		
		
	}
	
}
