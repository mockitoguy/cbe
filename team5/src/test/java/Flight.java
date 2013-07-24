import java.util.Collections;
import java.util.HashMap;

public class Flight {

    // STORY 2
    
    private String origin;
    private String destination;
    private String date;
    private String flightNo;

    public Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getFlightNo() {
        return flightNo;
    }

    
    
    
    
    // STORY 1

    @SuppressWarnings("rawtypes")
    private HashMap<String, HashMap> flights = new HashMap<String, HashMap>();
    private HashMap<String, Seat[]> flightsWithSeats = new HashMap<String, Seat[]>();

    public void addFlight(String string, HashMap<Integer, Integer> seats) {
        flights.put(string, seats);
    }

    public void addFlight(String string, Seat[] seats) {
        flightsWithSeats.put(string, seats);
    }

    public int getAvailableSeats(String string) {
        return flights.get(string).size();
    }

    @SuppressWarnings("unchecked")
    public int getPriceOfCheapestSeat(String string) {
        HashMap<Integer, Integer> seats = flights.get(string);
        return Collections.min(seats.values());
    }

    public int bookSeat(String flightNumber) {
        Seat[] seats = flightsWithSeats.get(flightNumber);
        for (int i = 0; i <= seats.length; i++) {
            if (!seats[i].isBooked()) {
                int seatNumber = seats[i].getNumber();
                seats[i].bookSeat(seatNumber);
                return seatNumber;
            }
        }
        return 0;
    }

    public boolean isBooked(String flightNumber, int seatNumber) {
        return flightsWithSeats.get(flightNumber)[seatNumber - 1].isBooked();
    }

}
