package team1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SunFlightManager implements FlightManager {

	Map<String, Flight> flights = new HashMap<String, Flight>();
	
	
	@Override
	public int getAvailableSeats(String string) {
		if(flights.containsKey(string)) {
			return flights.get(string).getAvailableSeats();
		}
		return -1;
	}

	@Override
	public void addFlight(Flight flight) {
		flights.put(flight.getId(), flight);		
	}

	@Override
	public double getCheapestSeat(String id) {
		Flight flight = flights.get(id);
		double price = Double.MAX_VALUE;
		for(Seat seat:flight.getSeats()) {
			if(seat.getPrice()<price) {
				price = seat.getPrice();
			}
		}
		return price;
	}

	@Override
	public boolean bookSeat(String id, int seatNo) {
		Flight flight = flights.get(id);
		List<Seat> seats = flight.getSeats();
		for(Seat seat:seats) {
			if(seat.getNumber() == seatNo) {
				if(seat.isAvailable()) {
					seat.setAvailable(false);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isSeatAvailable(String id, int seatNo) {
		Flight flight = flights.get(id);
		List<Seat> seats = flight.getSeats();
		for(Seat seat:seats) {
			if(seat.getNumber() == seatNo) {
				return seat.isAvailable();
			}
		}
		return false;
	}

	@Override
	public double getAveragePrice(String id, boolean b) {
		Flight flight = flights.get(id);
		List<Seat> seats = flight.getSeats();
		double sum = 0.0;
		int i=0;
		for(Seat seat:seats) {
			if(!b || seat.isAvailable()) {
				sum += seat.getPrice();
				i++;
			}
		}
		return sum/i;
	}

}
