import java.math.BigDecimal;

/**
 * @author Slawomir Bolimowski
 * @since 2014-01-31 11:56
 */
public class FlightTestDataGenerator {

    static Flight flightWithSeats(String flightNumber, int seatsNumber) {
        Flight flight = new Flight(flightNumber);
        for(int i =0; i < seatsNumber; i++) {
            flight.addSeat("" + (i + 1), BigDecimal.valueOf(i * 10));
        }
        return flight;
    }

    static Flight flightWithSeats(String flightNumber, String... seats) {
        Flight flight = new Flight(flightNumber);
        for (int i = 0; i < seats.length; i++) {
            String seat = seats[i];
            flight.addSeat(seat, BigDecimal.valueOf(i * 10));
        }
        return flight;
    }

    static Flight flightWithCheapestPrice(String flightNumber, BigDecimal price) {
        Flight flight = new Flight(flightNumber);
        flight.addSeat("1", price);
        flight.addSeat("2", price.add(BigDecimal.valueOf(1)));
        flight.addSeat("3", price.add(BigDecimal.valueOf(2)));
        flight.addSeat("4", price.add(BigDecimal.valueOf(3)));
        return flight;
    }
}
