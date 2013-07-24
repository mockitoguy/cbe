import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {

	Map<String, Integer> flights = new HashMap<String, Integer>();
	Map<String, List<Integer>> flightSeats = new HashMap<String, List<Integer>>();
	Map<String, List<Boolean>> flightBookedSeats = new HashMap<String, List<Boolean>>();

	public void add(String flightName, int availableSeats) {

		flights.put(flightName, availableSeats);

	}

	public int getAvailableSeats(String flightName) {

		int seats = flights.get(flightName);
		return seats;
	}

	public void addSeat(String flightName, List<Integer> seats) {
		flightSeats.put(flightName, seats);

	}

	public int getMin(List<Integer> seats) {

		int minPrice = seats.get(0);

		for (int i = 1; i < seats.size(); i++) {
			if (minPrice > seats.get(i)) {
				minPrice = seats.get(i);
			}
		}
		return minPrice;
	}

	public int getCheapestSeat(String flightName) {

		int minPrice = getMin(flightSeats.get(flightName));
		return minPrice;
	}

	public void addBookedSeats(String flightName, List<Boolean> availableSeats) {
		flightBookedSeats.put(flightName, availableSeats);

	}

	public boolean getBookedSeats(String flightName, int nrOfSeat) {
		boolean seat = flightBookedSeats.get(flightName).get(nrOfSeat);
		if (seat == true) {
			seat = flightBookedSeats.get(flightName).set(nrOfSeat, false);
			return true;
		} else
			return false;

	}

	public int getAvg(String flightName) {
		int temp = 0;
		int toAvg = 0;
		for(int i =0; i<flightBookedSeats.get(flightName).size();i++)
		{
			if (flightBookedSeats.get(flightName).get(i)==true)
			{
				temp+=flightSeats.get(flightName).get(i);
				toAvg++;
			}
				
		}
		
		int avg = temp/toAvg;
		return avg;
	}
}
