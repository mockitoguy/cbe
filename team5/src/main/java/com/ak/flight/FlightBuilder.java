package com.ak.flight;

import com.google.common.collect.Sets;
import org.joda.time.DateTime;

import java.util.EnumMap;
import java.util.Set;
import java.util.UUID;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class FlightBuilder {

  private String flightNumber = UUID.randomUUID().toString();
  private Set<Seat> seats = Sets.newHashSet();
  private String from;
  private String to;
  private DateTime time;
  private EnumMap<FlightClass,Long> defaultPriceMap = new EnumMap<FlightClass, Long>(FlightClass.class);

  public FlightBuilder withFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
    return this;
  }

  public FlightBuilder addSeat(Seat seat) {
    seats.add(seat);
    return this;
  }

  public Flight build() {
    return new Flight(flightNumber, seats, from, to, defaultPriceMap);
  }

  public FlightBuilder from(String from) {
    this.from = from;
    return this;
  }

  public FlightBuilder to(String to) {
    this.to = to;
    return this;
  }

  public FlightBuilder time(DateTime dateTime) {
    this.time = dateTime;
    return this;
  }

  public FlightBuilder addDefaultClassPrice(FlightClass flightClass, long defaultPrice) {
    defaultPriceMap.put(flightClass, defaultPrice);
    return this;
  }
}
