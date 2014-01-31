import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.valueOf;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 13:51
 */
public class FlightBuilder {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static int flightNumber = 1;
    private String number;
    private Map<String, BigDecimal> seats = new HashMap<>();
    private String origin = "Warsaw";
    private String destination = "London";
    private String date = "2014-06-06";

    public FlightBuilder() {
        this.number = "LOT-" + flightNumber++;
    }

    public FlightBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public Flight build() throws ParseException {
        Date flightDate = DATE_FORMAT.parse(date);
        Flight flight = new Flight(number, origin, destination, flightDate);
        for (Map.Entry<String, BigDecimal> entry : seats.entrySet()) {
            flight.addSeat(entry.getKey(), entry.getValue());
        }

        return flight;
    }

    public FlightBuilder addSeatWithPrice(BigDecimal price) {
        seats.put(Integer.toString(seats.size() + 1), price);
        return this;
    }

    public FlightBuilder addNumberOfSeats(int numberOfSeats) {
        for (int i = 0; i < numberOfSeats; ++i) {
            seats.put(Integer.toString(i + 1), valueOf(i * 10));
        }
        return this;
    }

    public FlightBuilder addSeats(String... seatNumbers) {
        for (int i = 0; i < seatNumbers.length; ++i) {
            String seatNumber = seatNumbers[i];
            seats.put(seatNumber, valueOf(i * 10));
        }
        return this;
    }

    public FlightBuilder setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public FlightBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public FlightBuilder setDate(String date) {
        this.date = date;
        return this;
    }
}
