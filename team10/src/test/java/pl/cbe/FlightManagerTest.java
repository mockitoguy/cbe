package pl.cbe;

import org.fest.assertions.Assertions;
import org.fest.assertions.Delta;
import org.junit.Test;

/**
 * @author bartosz.walacik
 */
public class FlightManagerTest {
	
	private static final String F_NO = "LOT01";

	//story 1
	@Test public void shouldKnowNoOfAvailSeats() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		
		//when
		int seats = fm.getAvailSeats(F_NO);
		
		//then
		Assertions.assertThat(seats).isEqualTo(5);
	}
	
	//story 2
	@Test public void shouldFindCheapestSeat() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,100);
					
		//when
		Seat seat = fm.getCheapestAvailSeat(F_NO);
		
		//then
		Assertions.assertThat(seat.getPrice()).isEqualTo(100, Delta.delta(0.1));
	}
	
	//story 3
	@Test
	public void shouldDecreaseAvailSeatsAfterBooking() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,100);
		Seat seat = fm.getCheapestAvailSeat(F_NO);
		
		//when
		fm.bookSeat(F_NO, seat.getSeatNo());
		int availSeats = fm.getAvailSeats(F_NO);
		
		//then
		Assertions.assertThat(availSeats).isEqualTo(2);
	}
	
	//story 4
	@Test
	public void shouldKnowAveragePriceOfNonBookedSeats() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,200);
		fm.addSeat(F_NO,100);
		
		//when
		double average = fm.getAveragePriceOfNonBookedSeats(F_NO);
		
		//then
		Assertions.assertThat(average).isEqualTo(500/3, Delta.delta(1));
	}
}
