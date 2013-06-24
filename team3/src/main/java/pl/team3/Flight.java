package pl.team3;

import java.util.ArrayList;
import java.util.List;


public class Flight {
    private final Seats seats;


    public Flight(List<Seat> seats) {
        if (seats == null) {
            seats = new ArrayList<Seat>();
        }
        this.seats = new Seats(seats);
    }

    public List<Seat> getSeats() {

        return seats.getSeats();
    }

    public List<Seat> getCheapestSeat() {

        return seats.getCheapestSeat();
    }

    public void addSeat(Seat seat) {

        seats.addSeat(seat);
    }
}
