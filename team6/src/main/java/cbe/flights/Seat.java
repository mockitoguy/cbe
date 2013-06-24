package cbe.flights;

/**
 * @author tskrobol
 */
public class Seat {
    private double price = 0d;
    private User bookedForUser;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public boolean bookForUser(User user) {
        if(isBooked())
            return false;
        this.bookedForUser = user;
        return true;
    }

    public boolean isBooked() {
        return bookedForUser != null;
    }
}
