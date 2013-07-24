package team1;

import java.util.List;

public interface Flight {

	String getId();
	
	int getAvailableSeats();
	
	List<Seat> getSeats();
	
}
