package team1;

import java.util.ArrayList;
import java.util.List;

public class FlightBuilder {

	FlightImpl flightImpl = new FlightImpl();
	
	FlightBuilder clean() {
		this.flightImpl = new FlightImpl();
		return this;
	}

	public FlightBuilder setId(String id) {
		flightImpl.setId(id);
		return this;
	}

	public FlightBuilder setAvailableSeats(int i) {
		flightImpl.setAvailableSeats(i);
		return this;
	}
	
	public FlightBuilder addSeat(int no, double price) {
		SeatImpl seat = new SeatImpl();
		seat.setNumber(no);
		seat.setPrice(price);
		this.flightImpl.getSeats().add(seat);
		return this;
	}
	
	

	Flight build() {
		return flightImpl;
	}
	
	

}

class FlightImpl implements Flight{

	String id;
	
	int availableSeats;

	private List<Seat> seats;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	
	
	public List<Seat> getSeats() {
		if(seats == null) {
			seats = new ArrayList<Seat>();
		}
		return seats;
	}
	
	
}

class SeatImpl implements Seat {
	private int number;
	private double price;
	private boolean available = true;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
}
