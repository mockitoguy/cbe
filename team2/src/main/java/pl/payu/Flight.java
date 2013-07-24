package pl.payu;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Flight {

    private String flightNo;
    private Map<Integer, Seat> seats = new HashMap<Integer, Seat>();
    private String origin;
    private String destination;
    private Date date;

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    public Flight(String flightNo, String origin, String destination, Date date) {
        this.flightNo = flightNo;
        this.setOrigin(origin);
        this.setDestination(destination);
        this.setDate(date);
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
        return sum / nonBookedSeats;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Seat findNonBookedSeat() {
        Collection<Seat> allSeats = seats.values();
        for (Seat seat : allSeats) {
            if (!seat.isBooked()) {
                return seat;
            }
        }
        return null;
    }

}
