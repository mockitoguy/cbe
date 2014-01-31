import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class FlightBuilder {
    private String flightNo;
    private List<Seat> seats = new ArrayList<>();
    private String from;
    private String to;

    private AtomicInteger seatIdCounter = new AtomicInteger();
    private Date date;

    public FlightBuilder() {
    }

    public FlightBuilder(String flightNo) {
        this.flightNo = flightNo;

    }

    public FlightBuilder withSeat(BigDecimal price) {
        Seat seat = new Seat(price, seatIdCounter.incrementAndGet());
        seats.add(seat);
        return this;
    }

    public FlightBuilder withSeat(BigDecimal price, int seatId) {
        Seat seat = new Seat(price, seatId);
        seats.add(seat);
        return this;
    }

    public FlightBuilder withSeat(Seat seat) {
        seats.add(seat);
        return this;
    }

    public FlightBuilder withBookedSeat(Seat seat) {
        seat.book();
        seats.add(seat);
        return this;
    }

    public FlightBuilder from(String from) {
        this.from = from;
        return this;
    }

    public FlightBuilder to(String to) {
        this.to = to;
        return this;
    }

    public FlightBuilder withBookedSeat(BigDecimal price) {
        Seat seat = new Seat(price, seatIdCounter.incrementAndGet());
        seat.book();
        seats.add(seat);
        return this;
    }

    public FlightBuilder on(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return this;
    }


    public Flight build() {
        Flight flight = new Flight(flightNo, seats);
        flight.setOrigin(from);
        flight.setDestination(to);
        flight.setDate(date);
        return flight;
    }

}
