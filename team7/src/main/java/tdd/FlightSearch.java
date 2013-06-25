package tdd;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

public class FlightSearch {

  public Set<Flight> searchFromOriginToDestination(
      String flightOrigin, String flightDestination, Collection<Flight> flights) {
    return search(
        Predicates.and(new FlightOriginPredicate(flightOrigin), new FlightDestinationPredicate(flightDestination)),
        flights);
  }

  public Set<Flight> searchFromOrigin(String flightOrigin, Collection<Flight> flights) {
    return search(new FlightOriginPredicate(flightOrigin), flights);
  }
  
  public Set<Flight> searchToDestination(String flightDestination, Collection<Flight> flights) {
    return search(new FlightDestinationPredicate(flightDestination), flights);
  }

  private Set<Flight> search(Predicate<Flight> searchPredicate, Collection<Flight> flights) {
    return new HashSet<Flight>(Collections2.filter(new HashSet<>(flights), searchPredicate));
  }

  private class FlightOriginPredicate implements Predicate<Flight> {

    private final String origin;

    public FlightOriginPredicate(String origin) {
      this.origin = origin;
    }

    @Override
    public boolean apply(Flight flight) {
      return origin.equals(flight.getOrigin());
    }
  }

  private class FlightDestinationPredicate implements Predicate<Flight> {

    private final String destination;

    public FlightDestinationPredicate(String destination) {
      this.destination = destination;
    }

    @Override
    public boolean apply(Flight flight) {
      return destination.equals(flight.getDestination());
    }
  }

}
