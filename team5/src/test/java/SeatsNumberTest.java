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
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight = Flight.createBuilder().withFlightCode("FR8723").withSeats(5).build();
        flightManager.addFlight(flight);

        //when
        int quantity = flightManager.getFlight("FR8723").getSeats();

        //then
        assertEquals(5, quantity);
    }
    @Test public void shouldReturnCheapestSeatPrice(){
        //given
        Flight flight = Flight.createBuilder().withDefaultPrice(100).withSeats(5).build();
        flight.setSeatPrice(2, 200);
        flight.setSeatPrice(4, 20);

        //when
        int cheapestSeatPrice = flight.getCheapestSeatPrice();

        //then
        assertEquals(20, cheapestSeatPrice);
    }
    @Test public void shouldBookASeat(){
        //given
        Flight flight = Flight.createBuilder().withSeats(5).build();

        //when
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(3);
        assertEquals(true, seat.isBooked());
    }
    @Test public void shouldNotBookASeat(){
        //given
        Flight flight = Flight.createBuilder().withSeats(5).build();

        //when
        flight.bookSeat(3);

        //then
        Seat seat = flight.getSeat(4);
        assertEquals(false, seat.isBooked());
    }
    @Test public void shouldReturnNotBookedSeatsAveragePrice(){
        //given
        Flight flight = Flight.createBuilder().withSeats(10).withDefaultPrice(20).build();
        flight.setSeatPrice(3, 200);
        flight.setSeatPrice(1, 11);
        flight.bookSeat(3);

        //when
        double average = flight.getNotBookedSeatsAveragePrice();

        //then
        assertEquals(19, average,0.0001);
    }

    @Test public void shouldReturnFlightsOnOriginDestinationDate(){
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = new Flight("FR8723",10,20,"Warsaw","Chicago",new Date());
        Flight flight2 = new Flight("FR2222",10,20,"Warsaw","Chicago",new Date());
        Flight flight3 = new Flight("FR1111",10,20,"Warsaw","Poznan",new Date());
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);


        //when
        List<Flight> flights = flightManager.getFlightsBetween("Warsaw","Chicago");

        //then
        assertThat(flights).contains(flight1).contains(flight2);
    }
    @Test public void shouldReturnFlightsFromGivenOrigin(){
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = Flight.createBuilder().withFlightCode("FR2222").withOrigin("Warsaw").build();
        Flight flight2 = Flight.createBuilder().withFlightCode("FR2223").withOrigin("Berlin").build();
        Flight flight3 = Flight.createBuilder().withFlightCode("FR2224").withOrigin("Warsaw").build();
        Flight flight4 = Flight.createBuilder().withFlightCode("FR2225").withOrigin("Warsaw").build();
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);
        flightManager.addFlight(flight4);


        //when
        List<Flight> flights = flightManager.getFlightsFrom("Warsaw");

        //then
        assertThat(flights).contains(flight1).contains(flight3).contains(flight4);
    }
    @Test public void shouldReturnFlightsFromGivenDestination(){
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight1 = Flight.createBuilder().withFlightCode("FR2222").withDestination("Chicago").build();
        Flight flight2 = Flight.createBuilder().withFlightCode("FR2223").withDestination("Warsaw").build();
        Flight flight3 = Flight.createBuilder().withFlightCode("FR2224").withDestination("Chicago").build();
        Flight flight4 = Flight.createBuilder().withFlightCode("FR2225").withDestination("Tokyo").build();
        flightManager.addFlight(flight1);
        flightManager.addFlight(flight2);
        flightManager.addFlight(flight3);
        flightManager.addFlight(flight4);


        //when
        List<Flight> flights = flightManager.getFlightsTo("Chicago");

        //then
        assertThat(flights).contains(flight1).contains(flight3);
    }
    @Test public void shouldReturnAveragePriceInAGivenClass(){
        //given
        Flight flight = new Flight("FR8723",100,20,"Warsaw","Chicago",new Date());
        flight.setSeatsClass(5,12,Seat.CLASS.BUSSINESS);
        flight.setSeatPrice(6,28);


        //when
        double average = flight.getSeatsAveragePrice(Seat.CLASS.BUSSINESS);

        //then
        assertThat(average).isEqualTo(21.0, Delta.delta(0.0001));
    }
}
