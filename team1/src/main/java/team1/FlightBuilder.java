package team1;

import java.util.ArrayList;
import java.util.Date;
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

	public FlightBuilder setOrgin(String orgin) {
		flightImpl.setOrgin(orgin);
		return this;
	}
	public FlightBuilder setDestination(String destination) {
		flightImpl.setDestination(destination);
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

	public FlightBuilder setDate(Date date) {
		flightImpl.setDate(date);
		return this;
	}


	
	

}

class FlightImpl implements Flight{

	String id;
	String orgin;
	String destination;
	int availableSeats;
	Date date;

	private List<Seat> seats;
	
	public String getId() {
		return id;
	}

	public void setDate(Date date1) {
		this.date = date1;
	}

	public Date getDate() {
		return date;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}
	public String getOrgin() {
		return this.orgin;
	}
	public void setDestination(String destination2) {
		this.destination = destination2;	
	}
	public String getDestination() {
		return destination;
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
