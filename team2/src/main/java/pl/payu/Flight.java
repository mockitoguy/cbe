package pl.payu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Flight {

    private String flightNo;
    private Map<Integer, Seat> seats = new HashMap<Integer, Seat>();
    
    public Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    public void addSeat(Seat seat) {
        this.seats.put(seat.getNo(), seat);
    }

    public String getFlightNo() {
        return flightNo;
    }

    public int getAvagetAvailableSeats() {
        return seats.size();
    }

    public double getCheapestSeatPrice() {
        Double price = null;
        Collection<Seat> allSeats = seats.values();
        for (Seat seat : allSeats) {
            if (price == null) {
                price = seat.getPrice();
            } else if (price.compareTo(seat.getPrice()) > 0) {
                price = seat.getPrice();
            }
        }
        return price;
    }

    public Seat findSeat(int seatNo) {
        return seats.get(seatNo);
    }

    public double getAveragePriceForNonBookedSeats() {
        double sum = 0;
        int nonBookedSeats = 0;
        Collection<Seat> allSeats = seats.values();
        for (Seat seat : allSeats) {
            if (!seat.isBooked()) {
                sum += seat.getPrice();
                nonBookedSeats++;
            }
        }
        return sum/nonBookedSeats;
    }

}
