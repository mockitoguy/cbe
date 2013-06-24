package pl.team3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pawelb
 * Date: 24.06.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class Flight {
    private List<Seat> seats;


    public Flight(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;

    }

    public List<Seat> getCheapestSeat() {

        if (seats == null || seats.isEmpty()) {
            return new ArrayList<Seat>();
        }
        List<Seat> results = new ArrayList<Seat>();
        long cheapestPrice = Long.MAX_VALUE;
        for (Seat seat : seats) {
            if (seat.getPrice() == cheapestPrice) {
                results.add(seat);
            } else if (seat.getPrice() < cheapestPrice) {
                results.clear();
                results.add(seat);
                cheapestPrice = seat.getPrice();
            }


        }
        return results;
    }

    public void addSeat(Seat seat) {
        if (seats == null) {
            seats = new ArrayList<Seat>();
        }
        this.seats.add(seat);

    }
}
