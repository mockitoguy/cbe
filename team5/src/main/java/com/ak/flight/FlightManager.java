package com.ak.flight;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class FlightManager {

  private Map<String, Flight> flights = Maps.newHashMap();

  public void addFlight(Flight flight) {
    flights.put(flight.getFlightNumber(), flight);
  }


  public Collection<Flight> getFlightsFromAirport(final String from) {
    return Collections2.filter(flights.values(), getFromPredicate(from));
  }

  public Collection<Flight> getFlightsToAirport(final String to) {
    return Collections2.filter(flights.values(), getToPredicate(to));
  }

  public Collection<Flight> getFlightsBetweenAirports(final String from, final String to) {
    return Collections2.filter(flights.values(), Predicates.and(getFromPredicate(from), getToPredicate(to)));
  }

  private Predicate<Flight> getFromPredicate(final String from) {
    return new Predicate<Flight>() {
      @Override public boolean apply(Flight flight) {
        return flight.getFrom().equals(from);
      }
    };
  }

  private Predicate<Flight> getToPredicate(final String to) {
    return new Predicate<Flight>() {
      @Override public boolean apply(Flight flight) {
        return flight.getTo().equals(to);
      }
    };
  }
}
