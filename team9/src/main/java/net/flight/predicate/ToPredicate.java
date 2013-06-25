package net.flight.predicate;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;
import net.flight.model.Flight;

public class ToPredicate implements Predicate<Flight> {

  private final String destination;

  public ToPredicate(String destination) {
    this.destination = destination;
  }

  @Override
  public boolean apply(@Nullable Flight flight) {
    return flight != null && destination.equals(flight.getDestination());
  }

}
