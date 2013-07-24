package team1;

public interface FlightManager {

	int getAvailableSeats(String string);

	void addFlight(Flight build);

	double getCheapestSeat(String id);

	boolean bookSeat(String string, int i);

	boolean isSeatAvailable(String string, int i);

	double getAveragePrice(String id, boolean b);

	
	
}
