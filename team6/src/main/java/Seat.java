import java.math.BigDecimal;

public  class Seat implements Comparable<Seat>{
    private final String number;
    private final BigDecimal price;

    public Seat(String number, BigDecimal price) {
        this.number = number;
        this.price = price;
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
}
