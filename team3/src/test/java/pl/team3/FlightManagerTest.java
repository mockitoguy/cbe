package pl.team3;

import org.junit.Before;
import org.junit.Test;
import pl.team3.Flight;
import pl.team3.FlightManager;
import pl.team3.Seat;

import java.util.ArrayList;
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

    public static final String FIRST_FLIGHT_NUMBER = "LOT123";
    public static final String SECOND_FLIGHT_NUMBER = "BA666";
    private static final long STANDARD_PRICE = 700L;
    private static final long CHEAPEST_PRICE = 500l;
    private FlightManager manager;
    private int firstSeatsNumber = 3;
    private int seccondSeatsNumber = 7;

    @Before
    public void setUp() throws Exception {
        manager = new FlightManager();
        manager.addFlight(FIRST_FLIGHT_NUMBER, new Flight(provideSeats(firstSeatsNumber)));
        manager.addFlight(SECOND_FLIGHT_NUMBER, new Flight(provideSeats(seccondSeatsNumber)));
    }

    @Test
    public void ShouldReturnAvailableSeatsNumber() throws Exception {

        //Given

        //When
        List<Seat> foundSeats = manager.getSeatsByFlightNumber(FIRST_FLIGHT_NUMBER);
        //Then
        assertThat(foundSeats.size()).isEqualTo(firstSeatsNumber);

    }

    @Test
    public void shouldReturnZeroForEmptyFlights() throws Exception {
        //Given
        manager = new FlightManager();

        //When
        List<Seat> foundSeats = manager.getSeatsByFlightNumber(FIRST_FLIGHT_NUMBER);
        //Then
        assertThat(foundSeats).isEmpty();


    }

    @Test
    public void shouldReturnTheCheapestSeatInFlight() throws Exception {
        //Given
        manager.getFlightByNumber(FIRST_FLIGHT_NUMBER).addSeat(provideCheapestSeat(666));
        //When

        List<Seat> cheapestSeats = manager.getCheapestSeatForFlight(FIRST_FLIGHT_NUMBER);
        //Then
        assertThat(cheapestSeats).isNotNull();
        assertThat(cheapestSeats).hasSize(1);
        assertThat(cheapestSeats.get(0).getPrice()).isEqualTo(CHEAPEST_PRICE);

    }

    @Test
    public void shouldReturnTheCheapestSeatsInFlight() throws Exception {
        //Given
        Flight fligt = manager.getFlightByNumber(FIRST_FLIGHT_NUMBER);
        fligt.addSeat(provideCheapestSeat(666));
        fligt.addSeat(provideCheapestSeat(667));

        //When

        List<Seat> cheapestSeats = manager.getCheapestSeatForFlight(FIRST_FLIGHT_NUMBER);
        //Then
        assertThat(cheapestSeats).isNotNull();
        assertThat(cheapestSeats).hasSize(2);
        for (Seat seat : cheapestSeats) {
            assertThat(seat.getPrice()).isEqualTo(CHEAPEST_PRICE);
        }

    }

    @Test
    public void shouldBookSeatForClient() throws Exception {
        //Given


        //When

        //Then
    }

    private List<Seat> provideSeats(int no) {
        List<Seat> result = new ArrayList<Seat>();
        for (int i = 0; i < no; i++) {
            result.add(provideSeat(i));
        }
        return result;
    }

    private Seat provideSeat(int seatNo) {
        return new Seat(STANDARD_PRICE, seatNo);
    }

    private Seat provideCheapestSeat(int seatNo) {
        return new Seat(CHEAPEST_PRICE, seatNo);
    }


}
