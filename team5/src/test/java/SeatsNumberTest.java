import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * @author: pgrela
 */
public class SeatsNumberTest {
    @Test
    public void shouldTellNumberOfAvailableSeatsOnTheFlight() {
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("FR8723",5, 100, "Warsaw", "Chicago", new Date());
        flightManager.addFlight(flight);

        //given
        int quantity = flightManager.getFlight("FR8723").getSeats();

        //then
        assertEquals(5, quantity);
    }
    @Test public void shouldReturnCheapestSeatPrice(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("FR8723",1,20, "Warsaw", "Chicago", new Date());
        flight.setSeatPrice(2, 200);
        flight.setSeatPrice(4,20);
        flightManager.addFlight(flight);

        //given
        int cheapestSeatPrice = flightManager.getFlight("FR8723").getCheapestSeatPrice();

        //then
        assertEquals(20, cheapestSeatPrice);
    }
    @Test public void shouldBookASeat(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());
        flightManager.addFlight(flight);

        //given
        flightManager.getFlight("FR8723").bookSeat(3);

        //then
        Seat seat = flightManager.getFlight("FR8723").getSeat(3);
        assertEquals(true, seat.isBooked());
    }
    @Test public void shouldNotBookASeat(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());
        flightManager.addFlight(flight);

        //given
        flightManager.getFlight("FR8723").bookSeat(3);

        //then
        Seat seat = flightManager.getFlight("FR8723").getSeat(4);
        assertEquals(false, seat.isBooked());
    }
    @Test public void shouldReturnNotBookedSeatsAveragePrice(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());
        flightManager.addFlight(flight);
        flightManager.getFlight("FR8723").bookSeat(3);
        flight.setSeatPrice(3, 200);
        flight.setSeatPrice(1, 11);

        //given
        double average = flightManager.getFlight("FR8723").getNotBookedSeatsAveragePrice();

        //then
        assertEquals(19, average,0.0001);
    }

    @Test public void shouldReturnFlightsOnOriginDestinationDate(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight1 = new Flight("FR8723",10,20,"Warsaw","Chicago",new Date());
        Flight flight2 = new Flight("FR2222",10,20,"Warsaw","Chicago",new Date());
        Flight flight3 = new Flight("FR1111",10,20,"Warsaw","Poznan",new Date());
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);


        //given
        List<Flight> flights = flightManager.getFlightsBetween("Warsaw","Chicago");

        //then
        assertThat(flights).contains(flight1).contains(flight2);
    }
}
