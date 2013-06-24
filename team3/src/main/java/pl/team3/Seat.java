package pl.team3;

public class Seat {
    private final long price;

    private final int seatNo;

    public Seat(long price, int seatNo) {
        this.price = price;
        this.seatNo = seatNo;

    }

    public int getSeatNo() {
        return seatNo;
    }

    public long getPrice() {
        return price;
    }
}
