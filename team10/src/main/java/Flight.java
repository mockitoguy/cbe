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

    public Flight(int seatsCount, Double defaultPrice) {
        this.seats = new ArrayList<Seat>(seatsCount);
        for (int i = 0; i < seatsCount; i++) {
            seats.add(i, new Seat(i, defaultPrice));
        }
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

    public double getCheapestSeatPrice() {
        Double cheapestPrice = Seat.UNSPECIFIED;
        for (Seat seat : seats) {

            Double seatPrice = seat.getPrice();
            if (cheapestPrice == Seat.UNSPECIFIED) {
                cheapestPrice = seatPrice;
            }

            if (seatPrice != Seat.UNSPECIFIED)
                cheapestPrice = Math.min(cheapestPrice, seatPrice);
        }
        return cheapestPrice;
    }
}
