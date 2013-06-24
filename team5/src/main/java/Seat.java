/**
 * @author: pgrela
 */
public class Seat {
    private int seatNumber;
    private int price;
    private boolean booked;
    private TRAVEL_CLASS aClass = TRAVEL_CLASS.ECONOMIC;

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

    public void setClass(TRAVEL_CLASS aClass) {
        this.aClass = aClass;
    }

    public TRAVEL_CLASS getaClass() {
        return aClass;
    }

    public void setaClass(TRAVEL_CLASS aClass) {
        this.aClass = aClass;
    }

    public enum TRAVEL_CLASS {ECONOMIC, BUSSINESS, FIRST}
}
