package pl.cbe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bartosz.walacik
 */
public class Flight {
	private String fNo;
	private Map<Integer, Seat> seats = new HashMap<>();
	
	public Flight(String fNo) {
		super();
		this.fNo = fNo;
	}
	public String getfNo() {
		return fNo;
	}
	public int getAvailSeats() {
		int avail = 0;
		for (Seat seat : seats.values()) {
			if (seat.isAvailable()) {
				avail++;
			}
		}
		
		return avail;
	}
	
	public Seat addSeat(double price) {
		int seatNo = seats.size()+1;
		
		Seat seat = new Seat(seatNo, price);
		seats.put(seatNo, seat);
		
		return seat;
	}	
	
	public Seat getCheapestAvailSeat() {
		double min = Double.MAX_VALUE;
		Seat   cheapest = null;
		
		for (Seat seat : seats.values()) {
			if (seat.getPrice() < min) {
				min = seat.getPrice();
				cheapest = seat;
			}
		}
		
		return cheapest;
	}
	public void bookSeat(int seatNo) {
		getSeat(seatNo).book();
		
	}
	
	private Seat getSeat(int seatNo) {
		if (!seats.containsKey(seatNo)) {
			throw new RuntimeException("no such seat");
		}
		
		return seats.get(seatNo);
	}
	
	public double getAveragePriceOfNonBookedSeats() {
		double sum = 0;
		for (Seat seat : seats.values()) {
			if (seat.isAvailable()) {
				sum += seat.getPrice();
			}
		}
		
		return sum / getAvailSeats();
	}
}
