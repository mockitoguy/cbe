package team1;

import java.util.Date;
import java.util.List;

public interface Flight {

	String getId();
	
	int getAvailableSeats();
	
	List<Seat> getSeats();

	String getDestination();
	
	String getOrgin();
	Date getDate();
}
