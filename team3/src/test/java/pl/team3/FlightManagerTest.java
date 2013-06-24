package pl.team3;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: pawelb
 * Date: 24.06.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
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
        Seat chepestSeat = new Seat(22, 80);
        manager.addFlight(new Flight.Builder("LOT123").addSeat(new Seat(1, 100)).addSeat(chepestSeat).build());
        //When

        List<Seat> cheapestSeats = manager.getCheapestSeatForFlight("LOT123");
        //Then
        assertThat(cheapestSeats).containsExactly(chepestSeat);

    }


}
