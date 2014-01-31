import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class Seat {

    private int seatId;
    private BigDecimal price;
    private boolean available = true;

    public Seat(BigDecimal price, int seatId) {
        this.price = price;
        this.seatId = seatId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getSeatId() {
        return seatId;
    }

    void book() {
        available = false;
    }

    boolean isAvailable() {
        return available;
    }
}
