import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class Seat {

    private BigDecimal price;

    public Seat(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
