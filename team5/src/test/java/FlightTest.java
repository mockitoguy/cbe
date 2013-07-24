import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class FlightTest {

    // STORY 2

    @Test
    public void shouldKnowFlightListFromTo() {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH001").origin("Warsaw").destination("Poznan").date("2013-08-01")
                .build());
        flightManager.addFlight(new FlightBuilder("LH002").origin("Warsaw").destination("Berlin").date("2013-08-02")
                .build());

        // when
        List<Flight> flight = flightManager.getFlight("Warsaw", "Poznan");

        // then
        assertEquals("LH001", flight.get(0).getFlightNo());
        assertEquals("2013-08-01", flight.get(0).getDate());
    }

    @Test
    public void shouldKnowFlightListFrom() {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH001").origin("Warsaw").destination("Poznan").date("2013-08-01")
                .build());
        flightManager.addFlight(new FlightBuilder("LH002").origin("Warsaw").destination("Berlin").date("2013-08-02")
                .build());

        // when
        List<Flight> flights = flightManager.getFlight("Warsaw");

        // then
        assertEquals("LH001", flights.get(0).getFlightNo());
        assertEquals("2013-08-01", flights.get(0).getDate());
        assertEquals("LH002", flights.get(1).getFlightNo());
        assertEquals("2013-08-02", flights.get(1).getDate());
    }

    @Test
    public void shouldKnowFlightListTo() {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH001").origin("Warsaw").destination("Poznan").date("2013-08-01")
                .build());
        flightManager.addFlight(new FlightBuilder("LH002").origin("Warsaw").destination("Berlin").date("2013-08-02")
                .build());

        // when
        List<Flight> flights = flightManager.getFlightWithDestination("Poznan");

        // then
        assertEquals("LH001", flights.get(0).getFlightNo());
        assertEquals("2013-08-01", flights.get(0).getDate());
    }

    
    
    // STORY 1

    // @Test
    // public void shouldKnowAvailableSeats() {
    // // given
    // Flight flight = new Flight();
    // HashMap<Integer, Integer> seats = new HashMap<Integer, Integer>();
    // seats.put(1, 1000);
    // seats.put(2, 200);
    // seats.put(3, 300);
    // HashMap<Integer, Integer> seats2 = new HashMap<Integer, Integer>();
    // seats2.put(1, 100);
    // flight.addFlight("LH001", seats);
    // flight.addFlight("LH002", seats2);
    //
    // // when
    // int availableSeats = flight.getAvailableSeats("LH001");
    //
    // // then
    // assertEquals(3, availableSeats);
    // }
    //
    // @Test
    // public void shouldGetPriceOfCheapestSeat() {
    // // given
    // Flight flight = new Flight();
    // HashMap<Integer, Integer> seats = new HashMap<Integer, Integer>();
    // seats.put(1, 1000);
    // seats.put(2, 200);
    // seats.put(3, 300);
    // flight.addFlight("LH001", seats);
    //
    // // when
    // int price = flight.getPriceOfCheapestSeat("LH001");
    //
    // // then
    // assertEquals(200, price);
    // }
    //
    // @Test
    // public void shouldBookSeat() {
    // // given
    // Flight flight = new Flight();
    // Seat seat1 = new Seat(1, 100, false);
    // Seat seat2 = new Seat(2, 200, false);
    // Seat seat3 = new Seat(3, 300, false);
    // Seat[] seats = {seat1, seat2, seat3};
    // flight.addFlight("LH001", seats);
    //
    // // when
    // int seatNumber = flight.bookSeat("LH001");
    //
    // // then
    // assertEquals(true, flight.isBooked("LH001", seatNumber));
    // }

}
