import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String name;
    private final List<Seat> seats;


    public Flight(int seatsCount) {
        this.seats = new ArrayList<Seat>(seatsCount);
        for (int i = 0; i < seatsCount; i++) {
            seats.add(i, new Seat(i));
        }
    }

    public void addSeat(int number, Double price) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }


    public int getSeatsCount() {
        return seats.size();
    }

}
