import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.fest.assertions.Delta;
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
        Flight flight = new Flight("FR8723",5,20, "Warsaw", "Chicago", new Date());
        flight.setSeatPrice(2, 200);
        flight.setSeatPrice(4, 20);

        //given
        int cheapestSeatPrice = flight.getCheapestSeatPrice();

        //then
        assertEquals(20, cheapestSeatPrice);
    }
    @Test public void shouldBookASeat(){
        //when
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());

        //given
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(3);
        assertEquals(true, seat.isBooked());
    }
    @Test public void shouldNotBookASeat(){
        //when
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());

        //given
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(4);
        assertEquals(false, seat.isBooked());
    }
    @Test public void shouldReturnNotBookedSeatsAveragePrice(){
        //when
        Flight flight = new Flight("FR8723",10,20, "Warsaw", "Chicago", new Date());
        flight.setSeatPrice(3, 200);
        flight.setSeatPrice(1, 11);
        flight.bookSeat(3);

        //given
        double average = flight.getNotBookedSeatsAveragePrice();

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
    @Test public void shouldReturnFlightsFromGivenOrigin(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight1 = new Flight("FR8723",10,20,"Warsaw","Chicago",new Date());
        Flight flight2 = new Flight("FR2222",10,20,"Tokyo","Chicago",new Date());
        Flight flight3 = new Flight("FR8724",10,20,"Warsaw","Tokyo",new Date());
        Flight flight4 = new Flight("FR1111",10,20,"Warsaw","Poznan",new Date());
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);
        flightManager.addFlight(flight4);


        //given
        List<Flight> flights = flightManager.getFlightsFrom("Warsaw");

        //then
        assertThat(flights).contains(flight1).contains(flight3).contains(flight4);
    }
    @Test public void shouldReturnFlightsFromGivenDestination(){
        //when
        FlightManager flightManager = new FlightManager();
        Flight flight1 = new Flight("FR8723",10,20,"Warsaw","Chicago",new Date());
        Flight flight2 = new Flight("FR2222",10,20,"Tokyo","Chicago",new Date());
        Flight flight3 = new Flight("FR8724",10,20,"Warsaw","Tokyo",new Date());
        Flight flight4 = new Flight("FR1111",10,20,"Warsaw","Poznan",new Date());
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);
        flightManager.addFlight(flight4);


        //given
        List<Flight> flights = flightManager.getFlightsTo("Chicago");

        //then
        assertThat(flights).contains(flight1).contains(flight2);
    }
    @Test public void shouldReturnAveragePriceInAGivenClass(){
        //when
        Flight flight = new Flight("FR8723",10,20,"Warsaw","Chicago",new Date());
        flight.setSeatsClass(5,12,Seat.CLASS.BUSSINESS);
        flight.setSeatPrice(6,28);


        //given
        double average = flight.getSeatsAveragePrice(Seat.CLASS.BUSSINESS);

        //then
        assertThat(average).isEqualTo(21.0, Delta.delta(0.0001));
    }
}
