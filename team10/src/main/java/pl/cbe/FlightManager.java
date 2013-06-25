package pl.cbe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author bartosz.walacik
 */
public class FlightManager {
	private Map<String,Flight> flights = new HashMap<>();
	
	public Flight addFlight(String fNo) {
		Flight f = new Flight(fNo);
		flights.put(fNo, f);
		
		return f;		
	}

	public int getAvailSeats(String fNo) {
		return getFlight(fNo).getAvailSeats();
	}

	public void addSeat(String fNo, double price) {
		getFlight(fNo).addSeat(price);
	}

	private Flight getFlight(String fNo) {
		if (!flights.containsKey(fNo)) {
			throw new RuntimeException("flight not found");
		}
		return flights.get(fNo);
	}

	public Seat getCheapestAvailSeat(String fNo) {
		Flight flight = flights.get(fNo);
		
		return flight.getCheapestAvailSeat();
	}

	public void bookSeat(String fNo, int seatNo) {
		Flight flight = flights.get(fNo);
		
		flight.bookSeat(seatNo);
	}

	public double getAveragePriceOfNonBookedSeats(String fNo) {
		Flight flight = flights.get(fNo);
		
		return flight.getAveragePriceOfNonBookedSeats();
	}
}
