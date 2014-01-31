import java.math.BigDecimal;

public  class Seat implements Comparable<Seat> {

    private final String number;
    private final BigDecimal price;
    private boolean booked;

    public Seat(String number, BigDecimal price) {
        this.number = number;
        this.price = price;
    }

    public void book() {
        booked = true;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int compareTo(Seat o) {
        return this.price.compareTo(o.price);
    }

    public boolean isBooked() {
        return booked;
    }

}
