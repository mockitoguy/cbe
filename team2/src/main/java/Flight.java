import java.util.ArrayList;
import java.util.List;

public class Flight {

    private final String flightNumber;
    private List<Seat> seats;

    public Flight(String flightNumber, int initialCapacity) {
        this.flightNumber = flightNumber;
        seats = new ArrayList<Seat>(initialCapacity);
        for(int i = 0 ; i< initialCapacity; i++){
            seats.add(new Seat());
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeatsCapacity() {
        return seats.size();
    }

    public Seat seatAt(int seatNumber) {
        return seats.get(seatNumber);
    }

    public List<Seat> getCheapestSeats() {
//        List<Seat> cheapestSeats = new ArrayList<Seat>();
//        for(Seat seat : seats){
//          //if(seat.)
//        }
        return null;
    }
}
