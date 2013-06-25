package net.flight.predicate;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;
import net.flight.model.Flight;

public class FromPredicate implements Predicate<Flight> {

  private final String origin;

  public FromPredicate(String origin) {
    this.origin = origin;
  }

  @Override
  public boolean apply(@Nullable Flight flight) {
    return flight != null && origin.equals(flight.getOrigin());
  }
}
