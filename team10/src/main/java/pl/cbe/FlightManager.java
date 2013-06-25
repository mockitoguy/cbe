package pl.cbe;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author bartosz.walacik
 */
public class FlightManager {
	private Map<String,Flight> flights = new HashMap<>();
	
	public Flight addFlight(String fNo, int availSeats) {
		Flight f = new Flight(fNo, availSeats);
		flights.put(fNo, f);
		
		return f;		
	}

	public int getAvailSeats(String fNo) {
		return getFlight(fNo).getAvailSeats();
	}

	public void setPrice(String fNo, int seatNo, double price) {
		getFlight(fNo).setPrice(seatNo, price);
	}

	private Flight getFlight(String fNo) {
		if (!flights.containsKey(fNo)) {
			throw new RuntimeException("flight not found");
		}
		return flights.get(fNo);
	}

	public double getCheapestAvailSeat(String fNo) {
		Flight flight = flights.get(fNo);
		
		return flight.getCheapestAvailSeat();
	}
}
