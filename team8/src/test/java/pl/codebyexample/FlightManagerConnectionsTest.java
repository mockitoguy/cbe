package pl.codebyexample;

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class FlightManagerConnectionsTest {

    FlightManager flightManager = new FlightManager();

    @Test
    public void shouldReturnFlightListBetweenOriginAndDestination() throws Exception {

        //given
        flightManager.addFlight(new FlightBuilder().withNumber("F12").withDate(new Date(2012,2,2)).withOrigin("WAW").withDestination("MOD").build());
        flightManager.addFlight(new FlightBuilder().withNumber("F13").withDate(new Date(2013,2,2)).withOrigin("WAW").withDestination("MOD").build());
        flightManager.addFlight(new FlightBuilder().withNumber("F14").withDate(new Date(2013,2,2)).withOrigin("WAW").withDestination("HTH").build());

        //when
        List<FlightInfo> flights = flightManager.flightsBetween("WAW", "MOD");

        //then
        Assertions.assertThat(flights.get(0).getNumber()).isEqualTo("F12");
        Assertions.assertThat(flights.get(0).getDate()).isEqualTo(new Date(2012, 2, 2));
        Assertions.assertThat(flights.get(1).getNumber()).isEqualTo("F13");
        Assertions.assertThat(flights.get(1).getDate()).isEqualTo(new Date(2013, 2, 2));
    }

}
