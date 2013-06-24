import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 15:11
 */
public class FlightBuilder {
    private Flight flight = new Flight();

    public FlightBuilder withNumber(String flightNumber) {
        flight.setFlightNumber(flightNumber);
        return this;
    }

    public FlightBuilder withSeat(int seatNo, BigDecimal price) {
        flight.addSeat(seatNo, price);
        return this;
    }

    public FlightBuilder withDestination(String destination) {
        flight.setDestination(destination);
        return this;
    }

    public FlightBuilder withOrigin(String origin) {
        flight.setOrigin(origin);
        return this;
    }

    public Flight build() {
        return flight;
    }

    public FlightBuilder withDate(Date date) {
        flight.setFlightDate(date);
        return this;
    }
}
