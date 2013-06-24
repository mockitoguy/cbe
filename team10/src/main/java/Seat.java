public class Seat {

    public static final Double PRICE_UNDEFINED = -1d;

    private final int position;
    private Double price;
    private boolean reserved = false;

    public Seat(int position, Double price) {
        this.position = position;
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPrice(Double price) {
        if (price == null) {
            price = PRICE_UNDEFINED;
        }
        this.price = price;
    }

    public Double getPrice() {
        return price;
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
