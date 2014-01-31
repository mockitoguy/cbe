import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Flight implements FlightDetails{

    private final String flightNumber;
    private List<Seat> seats;
    private String origin;
    private String destination;
    private DateTime dateTime;

    public Flight(String flightNumber, int initialCapacity) {
        this.flightNumber = flightNumber;
        seats = new ArrayList<Seat>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            seats.add(new Seat());
        }
    }

    Flight(String flightNumber, int initialCapacity, String origin, String destination, DateTime dateTime) {
        this(flightNumber, initialCapacity);
        this.dateTime = dateTime;
        this.origin = origin;
        this.destination = destination;
    }

    public Flight(String flightNumber, int initialCapacity,  String origin, String destination) {
       this(flightNumber, initialCapacity, origin, destination, null);
    }

    @Override
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

        List<Seat> cheapestSeats = new ArrayList<Seat>();
        if (seats.isEmpty()) {
            return cheapestSeats;
        } else {
            cheapestSeats.add(seatAt(0));
        }
        Seat cheapestSeat = seatAt(0);
        for (Seat seat : seats) {
            if(seat.getPrice() < cheapestSeat.getPrice()) {
                cheapestSeats.clear();
                cheapestSeats.add(seat);
                cheapestSeat = seat;
            } else if (seat.getPrice() == cheapestSeat.getPrice()){
                cheapestSeats.add(seat);
            }
        }
        return cheapestSeats;
    }


    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public DateTime getDateTime() {
        return dateTime;
    }
}
