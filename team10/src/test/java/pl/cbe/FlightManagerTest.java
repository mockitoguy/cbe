package pl.cbe;

import org.fest.assertions.Assertions;
import org.fest.assertions.Delta;
import org.junit.Test;

/**
 * @author bartosz.walacik
 */
public class FlightManagerTest {
	
	private static final String F_NO = "LOT01";

	@Test public void shouldKnowNoOfAvailSeats() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO,5);
		
		//when
		int seats = fm.getAvailSeats(F_NO);
		
		//then
		Assertions.assertThat(seats).isEqualTo(5);
	}
	
	@Test public void shouldFindCheapestSeat() {
		//given
		FlightManager fm = new FlightManager();
		fm.addFlight(F_NO,5);
		fm.setPrice(F_NO,1,200);
		fm.setPrice(F_NO,2,200);
		fm.setPrice(F_NO,3,100);
					
		//when
		double price = fm.getCheapestAvailSeat(F_NO);
		
		//then
		Assertions.assertThat(price).isEqualTo(100, Delta.delta(0.1));
	}
}
