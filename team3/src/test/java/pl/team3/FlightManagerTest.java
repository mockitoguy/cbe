package pl.team3;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FlightManagerTest {

    private FlightManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new FlightManager();
    }

    @Test
    public void ShouldReturnAvailableSeatsNumber() throws Exception {

        //Given
        manager.addFlight(new Flight.Builder("LOT123").addSeat(new Seat(1, 100)).addSeat(new Seat(2, 200)).build());
        manager.addFlight(new Flight.Builder("LOT123").addSeat(new Seat(2, 200)).build());

        //When
        List<Seat> foundSeats = manager.getSeatsByFlightNumber("LOT123");
        //Then
        assertThat(foundSeats.size()).isEqualTo(2);

    }

    @Test
    public void shouldReturnTheCheapestSeatInFlight() throws Exception {
        //Given
        Seat cheapestSeat = new Seat(3, 80);
        Seat anotherCheapestSeat = new Seat(2, 80);
        Seat seat = new Seat(1, 100);
        manager.addFlight(new Flight.Builder("LOT123").addSeat(seat).addSeat(cheapestSeat).addSeat(anotherCheapestSeat).build());
        //When

        List<Seat> cheapestSeats = manager.getCheapestSeatForFlight("LOT123");
        //Then
        assertThat(cheapestSeats).hasSize(2).contains(cheapestSeat).contains(anotherCheapestSeat);

    }

    @Test
    public void shouldReturnFligthBetweenPoints() throws Exception {
        //given                                                                            a
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("warsaw").flightDate(DateProvider.previousDate()).build());
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("oslo").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("warsaw").destination("london").build());
        //when
        List<Flight> flights = manager.findFlights("london", "warsaw");
        //then
        assertThat(flights.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnAllFlights() throws Exception {
        //given                                                                            a
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("warsaw").flightDate(DateProvider.previousDate()).build());
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("oslo").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("warsaw").destination("london").build());
        //when
        List<Flight> flights = manager.findFlights(null, null);
        //then
        assertThat(flights.size()).isEqualTo(3);
    }

    @Test
    public void shouldReturnFlightFrom() throws Exception {
        //given                                                                            a
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("warsaw").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("oslo").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("warsaw").destination("london").build());
        //when
        List<Flight> flights = manager.findFrom("london");
        //then
        assertThat(flights.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnFlightTo() throws Exception {
        //given                                                                            a
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("warsaw").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("london").destination("oslo").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("warsaw").destination("london").build());
        manager.addFlight(new Flight.Builder("LOT123").origin("new york").destination("london").build());
        //when
        List<Flight> flights = manager.findTo("london");
        //then
        assertThat(flights.size()).isEqualTo(2);
    }
}
