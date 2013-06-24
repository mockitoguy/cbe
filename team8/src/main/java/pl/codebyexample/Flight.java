package pl.codebyexample;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Flight {

    String flightNumber;
    HashSet<Seat> seats = new HashSet<Seat>();

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailableSeats() {
        int availableSeats = 0;
        for (Seat seat : seats) {
            if (seat.isAvailable())
            availableSeats++;
        }
        return availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public int getCheapestSeat() {
        int cheapest = Integer.MAX_VALUE;
        for (Seat seat : seats) {
            cheapest = cheapest > seat.getPrice()?seat.getPrice():cheapest;
        }
        return cheapest;
    }

    public boolean containsSeat(String seatNumber) {
        return false;
    }

    public boolean isSeatAvailable(String seatNumber) {
        return false;
    }

    public String bookSeat() {
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                seat.setAvailable(false);
                return seat.getNumber();
            }

        }
        return null;
    }
}
