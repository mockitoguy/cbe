package com.ak.flight;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
@Test
public class FlightManagerTest {

  public static final String DEST_WAW = "WAW";
  public static final String DEST_JFK = "JFK";
  private static final String DEST_AMS = "AMS";


  public void shouldReturnFlightsBetweenAirports() {
    //given
    FlightManager flightManager = new FlightManager();
    Flight waw2jfkFlight1 = new FlightBuilder().withFlightNumber("LOT103")
        .from(DEST_WAW).to(DEST_JFK).time(new DateTime(2013, 6, 26, 19, 29))
        .build();
    flightManager.addFlight(waw2jfkFlight1);

    Flight waw2jfkFlight2 = new FlightBuilder().withFlightNumber("AE345")
        .from(DEST_WAW).to(DEST_JFK).time(new DateTime(2013, 6, 26, 19, 29))
        .build();
    flightManager.addFlight(waw2jfkFlight2);

    flightManager.addFlight(new FlightBuilder().withFlightNumber("LOT208")
        .from(DEST_JFK).to(DEST_WAW).time(new DateTime(2013, 6, 26, 19, 29))
        .build());

    //when
    Collection<Flight> flights = flightManager.getFlightsBetweenAirports(DEST_WAW, DEST_JFK);

    //then
    assertThat(flights).containsOnly(waw2jfkFlight1, waw2jfkFlight2);
  }

  public void shouldReturnFlightsFromAirport() {
    //given
    FlightManager flightManager = new FlightManager();

    Flight waw2AmsFlight = new FlightBuilder()
        .from(DEST_WAW).to(DEST_AMS)
        .build();
    flightManager.addFlight(waw2AmsFlight);

    Flight waw2jfkFlight = new FlightBuilder()
        .from(DEST_WAW).to(DEST_JFK)
        .build();
    flightManager.addFlight(waw2jfkFlight);

    flightManager.addFlight(new FlightBuilder()
        .from(DEST_JFK).to(DEST_WAW)
        .build());

    //when
    Collection<Flight> flights = flightManager.getFlightsFromAirport(DEST_WAW);

    //then
    assertThat(flights).containsOnly(waw2AmsFlight, waw2jfkFlight);
  }

  public void shouldReturnFlightsToAirport() {
    //given
    FlightManager flightManager = new FlightManager();

    Flight ams2jfkFlight = new FlightBuilder()
        .from(DEST_AMS).to(DEST_JFK)
        .build();
    flightManager.addFlight(ams2jfkFlight);

    Flight waw2jfkFlight = new FlightBuilder()
        .from(DEST_WAW).to(DEST_JFK)
        .build();
    flightManager.addFlight(waw2jfkFlight);

    flightManager.addFlight(new FlightBuilder()
        .from(DEST_JFK).to(DEST_WAW)
        .build());

    //when
    Collection<Flight> flights = flightManager.getFlightsToAirport(DEST_JFK);

    //then
    assertThat(flights).containsOnly(ams2jfkFlight, waw2jfkFlight);
  }


}
