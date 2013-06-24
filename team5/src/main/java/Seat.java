/**
 * @author: pgrela
 */
public class Seat {
    private int seatNumber;
    private int price;
    private boolean booked;
    private CLASS aClass;

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Seat(int seatNumber, int price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setClass(CLASS aClass) {
        this.aClass = aClass;
    }

    public CLASS getaClass() {
        return aClass;
    }

    public void setaClass(CLASS aClass) {
        this.aClass = aClass;
    }

    public enum CLASS {ECONOMIC,BUSSINESS,FIRST}
}
