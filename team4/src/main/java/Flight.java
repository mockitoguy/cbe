import java.util.ArrayList;
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
    private List<Seat> seats = new ArrayList<>();

    Flight(String flighNo) {
        this.flightNo = flighNo;
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
}
