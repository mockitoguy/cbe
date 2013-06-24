package pl.team3;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: pawelb
 * Date: 24.06.13
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Seat {
    private final long price;

    public Seat(long price, int seatNo) {
        this.price = price;
        this.seatNo = seatNo;
    }

    private final int seatNo;

    public long getPrice() {
        return price;
    }
}
