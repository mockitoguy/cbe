import java.math.BigDecimal;

public class Seat {

    private int price = 100;
    private boolean available = true;

    public int getPrice() {
        return price;
    }

    public void setValue(int price) {
        this.price = price;
    }

    public void book() {
        this.available = false;
    }

    public boolean isAvailable() {
        return available;
    }
}
