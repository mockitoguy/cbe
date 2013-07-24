package pl.payu;

public class Seat {

    private int no;
    private double price;
    private boolean booked;

    public Seat(int no, double price) {
        this.no = no;
        this.price = price;
        this.booked = false;
    }

    public int getNo() {
        return no;
    }

    public double getPrice() {
        return price;
    }

    public void book() {
        this.booked = true;     
    }

    public boolean isBooked() {
        return booked;
    }

}
