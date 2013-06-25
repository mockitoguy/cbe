package net.flight;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;

public class DestinationPredicate implements Predicate<Flight> {

  private final String destination;

  public DestinationPredicate(String destination) {
    this.destination = destination;
  }

  @Override
  public boolean apply(@Nullable net.flight.Flight flight) {
    return flight != null && destination.equals(flight.getDestination());
  }

}
