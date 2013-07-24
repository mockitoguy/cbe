package pl.payu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.payu.exceptions.FlightNotExistsException;

public class FlightManagerTest {

    @Test
    public void shouldKnowAvailableSeats() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("LH101");
        flight.addSeat(new Seat(1, 10.0));
        flight.addSeat(new Seat(2, 20.0));
        flightManager.addFlight(flight);
        flightManager.addFlight(new Flight("AA202"));

        // when
        int seats = flightManager.getAvailableSeats("LH101");

        // then
        assertEquals(2, seats);

    }

    @Test
    public void shouldKnowCheapestSeatPrice() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("LH101");
        flight.addSeat(new Seat(1, 10.0));
        flight.addSeat(new Seat(2, 15.0));
        flightManager.addFlight(flight);
        // when
        double price = flightManager.getCheapestPrice("LH101");

        // then
        assertEquals(10.0d, price, 0.0);

    }

    @Test
    public void shouldBeAbleBookeSeat() {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("LH101");
        flight.addSeat(new Seat(1, 10.0));
        flight.addSeat(new Seat(2, 15.0));
        flightManager.addFlight(flight);
        flight.findSeat(1).book();
        // when
        Seat seat = flight.findSeat(1);
        // then
        assertTrue(seat.isBooked());
    }

    @Test
    public void shouldCheckAveragePriceOfNotBookedSeats() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("LH101");
        flight.addSeat(new Seat(1, 10.0));
        flight.addSeat(new Seat(2, 20.0));
        flight.addSeat(new Seat(3, 30.0));
        flightManager.addFlight(flight);
        flight.findSeat(1).book();
        
        // when
        Flight foundedFlight = flightManager.findFlight("LH101");
        // then
        assertEquals(25.0, foundedFlight.getAveragePriceForNonBookedSeats(), 0.0);
    }

    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlights() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new Flight("LH101"));
        
        //when
        flightManager.findFlight("LH102");
    }
    
    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlightsForAvailableSeats() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new Flight("LH101"));
        
        //when
        flightManager.getAvailableSeats("LH102");
    }
    
    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlightsForCheapestPrice() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new Flight("LH101"));
        
        //when
        flightManager.getAvailableSeats("LH102");
    }
}
