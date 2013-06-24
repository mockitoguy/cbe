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

    public List<Seat> getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsCount() {
        return seats.size();
    }

    public Double getSeatPrice(int seat) {
        return seats.get(seat).getPrice();
    }

    public void setSeatPrice(int seat, Double price) {
        seats.get(seat).setPrice(price);
    }


}
