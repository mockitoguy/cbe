
import com.google.common.base.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */
public class Flight {

    private String flightNo;
    private String origin;
    private String destination;
    private Date date;

    private List<Seat> seats = new ArrayList<>();

    Flight() {
    }

    Flight(String flightNo) {
        this.flightNo = flightNo;
    }

    Flight(String flightNo, List<Seat> seats) {
        this(flightNo);
        this.seats = new ArrayList<>(seats);
    }

    public Seat bookSeat(int seatId) throws SeatNotFoundException {
        for (Seat seat : seats) {
            if (seat.getSeatId() == seatId) {
                seat.book();
                return seat;
            }
        }

        throw new SeatNotFoundException();
    }

    public boolean isSeatAvailable(int seatNumber) throws SeatNotFoundException {
        for (Seat seat : seats) {
            if (seat.getSeatId() == seatNumber) {
                return seat.isAvailable();
            }
        }

        throw new SeatNotFoundException();
    }

    public BigDecimal getAveragePriceOfNonBookedSeats() {
        BigDecimal sum = BigDecimal.ZERO;
        int count = 0;
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                count++;
                sum = sum.add(seat.getPrice());
            }
        }

        return count != 0 ? sum.divide(BigDecimal.valueOf(count)) : BigDecimal.ZERO;
    }

    public int getAvailableSeatCount() {
        return seats.size();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }
}
