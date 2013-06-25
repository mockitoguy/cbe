package tdd;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tdd.builder.FlightBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class FlightSearchTest {

  private String flightOrigin1;
  private String flightOrigin2;

  private String flightDestination1;
  private String flightDestination2;

  private Flight flight1;
  private Flight flight2;

  private FlightSearch flightSearch;

  private String unavailableOrigin;
  private String unavailableDestination;

  @Before
  public void setUp() {
    flightOrigin1 = "flightOrigin1";
    flightOrigin2 = "flightOrigin2";

    flightDestination1 = "flightDestination1";
    flightDestination2 = "flightDestination2";

    flight1 = new FlightBuilder().withOriginAndDestination(flightOrigin1, flightDestination1).build();
    flight2 = new FlightBuilder().withOriginAndDestination(flightOrigin2, flightDestination2).build();

    flightSearch = new FlightSearch();

    unavailableOrigin = "unavailable origin";
    unavailableDestination = "unavailable destination";
  }

  @Test
  public void shouldFindFlightsFromOriginToDestination() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchFromOriginToDestination(flightOrigin1, flightDestination1, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(flight1), flights);
  }

  @Test
  public void shouldReturnEmptySetWhenNoFlightFromOriginToDestination() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchFromOriginToDestination(unavailableOrigin, unavailableDestination, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(), flights);
  }
  
  @Test
  public void shouldFindFlightsFromOrigin() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchFromOrigin(flightOrigin1, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(flight1), flights);
  }

  @Test
  public void shouldReturnEmptySetWhenNoFlightFromOrigin() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchFromOrigin(unavailableOrigin, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(), flights);
  }

  @Test
  public void shouldFindFlightsToDestination() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchToDestination(flightDestination1, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(flight1), flights);
  }
  
  @Test
  public void shouldReturnEmptySetWhenNoFlightToDestination() throws Exception {
    // given

    // when
    Set<Flight> flights = flightSearch
        .searchToDestination(unavailableDestination, Lists.newArrayList(flight1, flight2));

    // then
    assertEquals(Sets.newHashSet(), flights);
  }

}

