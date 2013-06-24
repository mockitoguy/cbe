public class Seat {
    public static Double UNSPECIFIED = -1d;

    private int position;
    private Double price = UNSPECIFIED;
    private boolean reserved = false;

    public Seat(int position) {
        this.position = position;
    }

    public Seat(int position, Double price) {
        this.position = position;
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void reserve() {
        if (!reserved)
            reserved = true;
        else
            throw new SeatAlreadyReserved();
    }

    public boolean isReserved() {
        return reserved;
    }
}
