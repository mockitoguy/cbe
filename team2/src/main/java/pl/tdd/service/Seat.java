package pl.tdd.service;

/**
 * User: pcierpiatka
 */
public class Seat {

    private String seatNumber;

    private SeatClass seatClass;

    private double price;

    private boolean booked;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    public Seat(SeatClass seatClass, String seatNumber, double price) {
        this(seatNumber);
        this.seatClass = seatClass;
        this.price = price;
    }

    public void book() {
        this.booked = true;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public boolean isInClass(SeatClass seatClass) {
        return this.seatClass.equals(seatClass);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isAvailable() {
        return !isBooked();
    }

}
