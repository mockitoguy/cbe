import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class FlightManagerTest {
	
	@Test
	public void shouldReturnAvailableSeats() {
		
		//given
		FlightManager flightmanager = new FlightManager();
		flightmanager.add("FL666",10);
		flightmanager.add("AL675",25);
		
		//when
		int seats = flightmanager.getAvailableSeats("AL675");
		
		//then
		assertEquals(25,seats);
	}
	
	@Test
	public void shouldReturnCheapestSeat() {
		
		//given
		FlightManager flightmanager = new FlightManager();
		List <Integer> seatsFL666 = new ArrayList<Integer>();
		seatsFL666.add(100);
		seatsFL666.add(56);
		seatsFL666.add(23);
		flightmanager.addSeat("FL666",seatsFL666);
		
		List <Integer> seatsADT667 = new ArrayList<Integer>();
		seatsADT667.add(100);
		seatsADT667.add(56);
		seatsADT667.add(13);
		flightmanager.addSeat("ADT",seatsADT667);
		
		//when
		int minPrice = flightmanager.getCheapestSeat("FL666");
		
		//then
		assertEquals(23,minPrice);
	}
	
	@Test
	public void shouldBookSeat() {
		
		//given
		FlightManager flightmanager = new FlightManager();
		List<Boolean> availableSeats = new ArrayList<Boolean>();
		availableSeats.add(true);
		availableSeats.add(false);
		availableSeats.add(true);
		flightmanager.addBookedSeats("FL666",availableSeats);
		
		//when
		boolean seats = flightmanager.getBookedSeats("FL666",2);
		
		//then
		assertTrue(seats);
	}
	
	@Test
	public void shouldCalculateAvgOfNonBookedSeats() {
		
		//given
		FlightManager flightmanager = new FlightManager();
		
		List<Boolean> availableSeats = new ArrayList<Boolean>();
		availableSeats.add(true);
		availableSeats.add(true);
		availableSeats.add(false);
		flightmanager.addBookedSeats("FL666",availableSeats);
		
		List <Integer> seatsFL666 = new ArrayList<Integer>();
		seatsFL666.add(100);
		seatsFL666.add(56);
		seatsFL666.add(23);
		flightmanager.addSeat("FL666",seatsFL666);
		
		//when
		int avg = flightmanager.getAvg("FL666");
		
		//then
		assertEquals(78, avg);
	}

}
