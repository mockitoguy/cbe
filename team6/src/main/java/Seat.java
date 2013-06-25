public class Seat {
    private String seatNo;
    private boolean booked = false;

    private long price;


    public Seat(String seatNo, boolean booked, long price) {
        this.seatNo = seatNo;
        this.booked = booked;
        this.price = price;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public long getPrice() {
        return price;
    }
}
