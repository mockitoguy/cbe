package net.flight;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;

public class OriginPredicate implements Predicate<Flight> {

  private final String origin;

  public OriginPredicate(String origin) {
    this.origin = origin;
  }

  @Override
  public boolean apply(@Nullable net.flight.Flight flight) {
    return flight != null && origin.equals(flight.getOrigin());
  }
}
