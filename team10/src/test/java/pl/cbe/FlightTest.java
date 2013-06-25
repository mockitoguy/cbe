package pl.cbe;

import static org.junit.Assert.*;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class FlightTest {

	@Test
	public void shouldKnowAvailableSeats() {
		//given
		Flight flight = new Flight("LH10");
		flight.addSeat(10.0);
		flight.addSeat(20.0);
		
		//when
		int seats = flight.getAvailSeats();
		
		//then
		assertEquals(2, seats);
	}

	//story 3
	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionWhenSeatAlreadyBooked() {
		//given
		Flight flight = new Flight("LH10");
		Seat seat = flight.addSeat(10.0);
		
		//when
		flight.bookSeat(seat.getSeatNo());
		flight.bookSeat(seat.getSeatNo());
	}
}
