package team1;

import java.util.Date;
import java.util.List;

import team1.Seat.SeatClass;

public interface Flight {

	String getId();
	
	int getAvailableSeats();
	
	List<Seat> getSeats();

	String getDestination();
	
	String getOrgin();
	Date getDate();
	public Double getSeatClassePrice(SeatClass buissnesClass);
}
