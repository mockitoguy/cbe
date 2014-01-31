import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 10:52
 */
public class Flight {
    private final String origin;
    private final String destination;
    private final Date date;
    private String number;
    private Map<String, BigDecimal> seats = new HashMap<>();

    public Flight(String number, String origin, String destination, Date date) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public void addSeat(String number, BigDecimal price) {
        seats.put(number, price);
    }

    public String getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }

    public int getAvailableSeats() {
        return seats.size();
    }

    public BigDecimal getCheapestSeatPrice() {
        if (seats.isEmpty()) {
            throw new FlightHasNoSeatsException(number);
        }

        BigDecimal cheapestPrice = BigDecimal.valueOf(Long.MAX_VALUE);
        for (BigDecimal price : seats.values()) {
            if (price.compareTo(cheapestPrice) < 0) {
                cheapestPrice = price;
            }
        }

        return cheapestPrice;
    }

    public void bookSeat(String seatNumber) {
        if (seats.containsKey(seatNumber)) {
            seats.remove(seatNumber);
        }
    }

    public BigDecimal getAveragePrice() {
        if (seats.isEmpty()) {
            throw new FlightHasNoSeatsException(number);
        }

        BigDecimal sum = BigDecimal.ZERO;

        for (BigDecimal price : seats.values()) {
            sum = sum.add(price);
        }

        return sum.divide(BigDecimal.valueOf(seats.size()));
    }

    public boolean matchOriginAndDestination(String origin, String destination) {
        return matchOrigin(origin) && matchDestination(destination);
    }

    public boolean matchOrigin(String origin) {
        checkArgument(origin != null, "Origin must not be null!");
        return origin.equals(this.origin);
    }

    public boolean matchDestination(String destination) {
        checkArgument(destination != null, "Destination must not be null");
        return destination.equals(this.destination);
    }
}
