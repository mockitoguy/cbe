import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class FlightTest {

    @Test
    public void shouldKnowAvailableSeats() {
        // given
        Flight flight = new Flight();
        HashMap<Integer, Integer> seats = new HashMap<Integer, Integer>();
        seats.put(1, 1000);
        seats.put(2, 200);
        seats.put(3, 300);
        HashMap<Integer, Integer> seats2 = new HashMap<Integer, Integer>();
        seats2.put(1, 100);
        flight.addFlight("LH001", seats);
        flight.addFlight("LH002", seats2);

        // when
        int availableSeats = flight.getAvailableSeats("LH001");

        // then
        assertEquals(3, availableSeats);
    }

    @Test
    public void shouldGetPriceOfCheapestSeat() {
        // given
        Flight flight = new Flight();
        HashMap<Integer, Integer> seats = new HashMap<Integer, Integer>();
        seats.put(1, 1000);
        seats.put(2, 200);
        seats.put(3, 300);
        flight.addFlight("LH001", seats);

        // when
        int price = flight.getPriceOfCheapestSeat("LH001");

        // then
        assertEquals(200, price);
    }

    @Test
    public void shouldBookSeat() {
        // given
        Flight flight = new Flight();
        Seat seat1 = new Seat(1, 100, false);
        Seat seat2 = new Seat(2, 200, false);
        Seat seat3 = new Seat(3, 300, false);
        Seat[] seats = {seat1, seat2, seat3};
        flight.addFlight("LH001", seats);

        // when
        int seatNumber = flight.bookSeat("LH001");

        // then
        assertEquals(true, flight.isBooked("LH001", seatNumber));
    }
    
}
