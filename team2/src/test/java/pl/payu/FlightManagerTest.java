package pl.payu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

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
        flightManager.addFlight(new FlightBuilder("LH101").seat().seat().build());
        Seat seat = null;
        try {
            seat = flightManager.findNonBookedSeat("LH101");
            seat.book();
        } catch (FlightNotExistsException e) {
            fail();
        }
        try {
            // when
            Seat bookedSeat = flightManager.findSeat("LH101", seat.getNo());
            // then
            assertTrue(bookedSeat.isBooked());
        } catch (FlightNotExistsException e) {
           fail();
        }
    }

    @Test
    public void shouldCheckAveragePriceOfNotBookedSeats() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH101").seat(1, 10.0).seat(2, 20.0).seat(3, 30.0).build());
        flightManager.findSeat("LH101", 1).book();
        
        // when
        Flight foundedFlight = flightManager.findFlight("LH101");
        // then
        assertEquals(25.0, foundedFlight.getAveragePriceForNonBookedSeats(), 0.0);
    }

    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlights() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH101").build());
        
        //when
        flightManager.findFlight("LH102");
    }
    
    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlightsForAvailableSeats() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH101").build());
        
        //when
        flightManager.getAvailableSeats("LH102");
    }
    
    @Test(expected = FlightNotExistsException.class)
    public void shouldReportNotExistingFlightsForCheapestPrice() throws FlightNotExistsException {
        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight(new FlightBuilder("LH101").build());
        
        //when
        flightManager.getAvailableSeats("LH102");
    }
    
    @Test
    public void shouldListFlightsForGivenOriginAndDestination() {
        //given
        FlightManager flightManager = new FlightManager();
        Date date1 = new Date();
        flightManager.addFlight(new FlightBuilder("PK101").origin("Poznan").destination("Krakow").date(date1).build());
        flightManager.addFlight(new FlightBuilder("PK102").origin("Poznan").destination("Krakow").date(date1).build());
        Date date2 = new Date();
        flightManager.addFlight(new FlightBuilder("WP101").origin("Warszawa").destination("Poznan").date(date2).build());
        
        //when
        List<Flight> flights = flightManager.findFlights("Poznan", "Krakow");
        
        //then
        assertEquals(flights.size(), 2);
        assertEquals(flights.get(0).getDate(), date1);
        assertEquals(flights.get(0).getFlightNo(), "PK101");
    }
    
    @Test
    public void shouldListForGivenOrigin() {
      //given
        FlightManager flightManager = new FlightManager();
        Date date1 = new Date();
        Date date2 = new Date();
        flightManager.addFlight(new FlightBuilder("PK101").origin("Poznan").date(date1).build());
        flightManager.addFlight(new FlightBuilder("WP101").origin("Krakow").date(date2).build());
        flightManager.addFlight(new FlightBuilder("PK102").origin("Poznan").date(date1).build());

        
        //when
        List<Flight> flights = flightManager.findFlightsByOrigin("Poznan");
        
        //then
        assertEquals(flights.size(), 2);
        assertEquals(flights.get(0).getDate(), date1);
        assertEquals(flights.get(0).getFlightNo(), "PK101");
        
    }

    @Test
    public void shouldListForGivenDestination() {
      //given
        FlightManager flightManager = new FlightManager();
        Date date1 = new Date();
        Date date2 = new Date();
        flightManager.addFlight(new FlightBuilder("PK101").destination("Krakow").date(date1).build());
        flightManager.addFlight(new FlightBuilder("WP101").destination("Poznan").date(date2).build());
        flightManager.addFlight(new FlightBuilder("PK102").destination("Krakow").date(date1).build());

        //when
        List<Flight> flights = flightManager.findFlightsByDestination("Krakow");
        
        //then
        assertEquals(flights.size(), 2);
        assertEquals(flights.get(0).getDate(), date1);
        assertEquals(flights.get(0).getFlightNo(), "PK101");
        
    }
    
}
