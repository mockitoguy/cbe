package pl.cbe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bartosz.walacik
 */
public class Flight {
	private String fNo;
	private int availSeats;
	private Map<Integer, Double> prices = new HashMap<>();
	
	public Flight(String fNo, int availSeats) {
		super();
		this.fNo = fNo;
		this.availSeats = availSeats;
	}
	public String getfNo() {
		return fNo;
	}
	public int getAvailSeats() {
		return availSeats;
	}
	public void setPrice(int seatNo, double price) {
		prices.put(seatNo, price);
	}	
	public double getCheapestAvailSeat() {
		double min = Double.MAX_VALUE;
		
		for (double p : prices.values()) {
			if (p < min) {
				min = p;
			}
		}
		
		return min;
	}
}
