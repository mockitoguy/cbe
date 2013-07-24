package team1;

import java.util.List;

import team1.Seat.SeatClass;



public interface FlightManager {

	int getAvailableSeats(String string);

	void addFlight(Flight build);

	double getCheapestSeat(String id);

	boolean bookSeat(String string, int i);

	boolean isSeatAvailable(String string, int i);

	double getAveragePrice(String id, boolean b);

	double getAveragePrice(String id, SeatClass business);

	List<Flight> getFlightsBetween(String string, String string2);

	Flight getFlight(String flight);

	List<Seat> getUntypicalPriceSeats(String string);


	
	
}
