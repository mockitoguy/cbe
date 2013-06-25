public class SeatBuilder {
    private String seatNo;
    private boolean booked = false;
    private long price;

    public SeatBuilder setSeatNo(String seatNo) {
        this.seatNo = seatNo;
        return this;
    }

    public SeatBuilder booked() {
        this.booked = true;
        return this;
    }

    public SeatBuilder free() {
        this.booked = false;
        return this;
    }

    public Seat createSeat() {
        return new Seat(seatNo, booked, price);
    }

    public SeatBuilder setPrice(Long price) {
        this.price = price;
        return this;
    }
}