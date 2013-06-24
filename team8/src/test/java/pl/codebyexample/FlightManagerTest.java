package pl.codebyexample;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class FlightManagerTest {

    @Test
    public void shouldReturnAvailableSeatsForFlight() {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight= new Flight("F12");
        for (int i = 0; i < 5; i++) {
            flight.addSeat(new Seat("" + i, 120));
        }
        flightManager.addFlight(flight);

        //expect
        Assert.assertEquals(5, flightManager.getSeatCountForFlightNumber("F12"));
    }

    @Test
    public void shouldReturnTheCheapestSeatForAFlight(){
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("F12");
        for (int i = 0; i < 10; i++) {
            flight.addSeat(new Seat(""+i,  120 +i ));
        }
        flightManager.addFlight(flight);

        //expect
        Assert.assertEquals(120, flightManager.getTheCheapestSeatPriceForFlightNumber("F12"));
    }

    @Test
    public void shouldBookAndReturnSeatNumber() throws Exception {
        //given
        FlightManager flightManager = new FlightManager();
        Flight flight = new Flight("F12");
        for (int i = 0; i < 10; i++) {
            flight.addSeat(new Seat("" + i, 120));
        }
        flightManager.addFlight(flight);

        //when
        String seatNumber = flightManager.bookSeatForFlightNumber("F12");

        //then
        Assert.assertTrue(flight.containsSeat(seatNumber));
        Assert.assertTrue(flight.isSeatAvailable(seatNumber));
    }
}
