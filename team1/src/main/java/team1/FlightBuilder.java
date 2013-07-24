package team1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import team1.Seat.SeatClass;

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
	
	public FlightBuilder addSeat(int no, double price, SeatClass clazz) {
		SeatImpl seat = new SeatImpl();
		seat.setNumber(no);
		seat.setPrice(price);
		seat.setClazz(clazz);
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

	public FlightBuilder addSeatClassPrice(SeatClass business, double d) {
		flightImpl.setSeatClassPrice(business,d);
		return this;
	}


	
	

}

class FlightImpl implements Flight{

	String id;
	String orgin;
	String destination;
	int availableSeats;
	Date date;
	HashMap<SeatClass,Double> seatClassDefPrices = new HashMap<SeatClass,Double>();

	private List<Seat> seats;
	
	public String getId() {
		return id;
	}

	public void setSeatClassPrice(SeatClass business, double d) {
		seatClassDefPrices.put(business, d);
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

	@Override
	public Double getSeatClassePrice(SeatClass buisnessClass) {
		return seatClassDefPrices.get(buisnessClass);
	}
	
	
}

class SeatImpl implements Seat {
	private int number;
	private double price;
	private boolean available = true;
	private SeatClass clazz;
	
	public SeatClass getClazz() {
		return clazz;
	}
	public void setClazz(SeatClass clazz) {
		this.clazz = clazz;
	}
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
