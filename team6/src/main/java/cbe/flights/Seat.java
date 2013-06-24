package cbe.flights;

/**
 * @author tskrobol
 */
public class Seat {
    private Flight flight;
    private final SeatClass seatClass;
    private double price = 0d;
    private User bookedForUser;

    public Seat(double price, SeatClass seatClass) {
        this.price = price;
        this.seatClass = seatClass;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public boolean bookForUser(User user) {
        if (isBooked())
            return false;
        this.bookedForUser = user;
        return true;
    }

    public boolean isBooked() {
        return bookedForUser != null;
    }

    public Flight getFlight() {
        return flight;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
