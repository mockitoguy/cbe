package com.ak.flight;

import com.ak.flight.exception.NoSuchSeatException;
import com.ak.flight.exception.NonFreeSeatsAvailableException;
import com.ak.flight.exception.SeatAlreadyBookedException;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author jkubrynski@gmail.com
 * @since 2013-06-25
 */
public class Flight {

  private final String flightNumber;
  private final String from;
  private final String to;
  private final Map<String, Seat> seats;
  private EnumMap<FlightClass, Long> defaultPriceMap;

  public Flight(String flightNumber, Set<Seat> seats, String from, String to, EnumMap<FlightClass, Long> defaultPriceMap) {
    this.flightNumber = flightNumber;
    this.from = from;
    this.to = to;
    this.defaultPriceMap = defaultPriceMap;
    this.seats = buildSeatsMap(seats);
  }

  private Map<String, Seat> buildSeatsMap(Set<Seat> seats) {
    HashMap<String, Seat> resultMap = Maps.newHashMap();
    for (Seat seat : seats) {
      resultMap.put(seat.getSeatNumber(), seat);
    }

    return resultMap;
  }

  public void bookSeat(String seatNo) throws SeatAlreadyBookedException, NoSuchSeatException {
    Seat seat = seats.get(seatNo);
    if (seat == null) {
      throw new NoSuchSeatException();
    }
    seat.book();
  }

  public long getLowestSeatPrice() {
    long lowestPrice = Long.MAX_VALUE;
    for (Seat seat : seats.values()) {
      if (seat.getPriceInCents() < lowestPrice) {
        lowestPrice = seat.getPriceInCents();
      }
    }

    return lowestPrice;
  }

  public long getAveragePriceOfFreeSeats() throws NonFreeSeatsAvailableException {
    final Collection<Seat> freeSeats = getFreeSeats();
    if (freeSeats.isEmpty()) {
      throw new NonFreeSeatsAvailableException();
    }

    return countAveragePrice(freeSeats);
  }

  public long getAveragePriceForClass(final FlightClass flightClass) {
    Collection<Seat> seatCollection = Collections2.filter(seats.values(), new Predicate<Seat>() {
      @Override public boolean apply(Seat input) {
        return flightClass == input.getFlightClass();
      }
    });
    return countAveragePrice(seatCollection);
  }

  public int getAvailableSeatsCount() {
    return getFreeSeats().size();
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public Seat getSeat(String seatNumber) {
    return seats.get(seatNumber);
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  private Collection<Seat> getFreeSeats() {
    return Collections2.filter(seats.values(), new Predicate<Seat>() {
      @Override public boolean apply(Seat seat) {
        return !seat.isBooked();
      }
    });
  }

  private long countAveragePrice(Collection<Seat> freeSeats) {
    long sum = 0;
    for (Seat seat : freeSeats) {
      sum += seat.getPriceInCents();
    }
    return sum / freeSeats.size();
  }

  public int getSeatsCountWithoutDefaultClassPrice(final FlightClass flightClass) {
    Collection<Seat> seatCollection = Collections2.filter(getSeatsFromFlightClass(flightClass), new Predicate<Seat>() {
      @Override public boolean apply(Seat input) {
        return input.getPriceInCents() != defaultPriceMap.get(flightClass);
      }
    });
    return seatCollection.size();
  }

  private Collection<Seat> getSeatsFromFlightClass(final FlightClass flightClass) {
    return Collections2.filter(seats.values(), new Predicate<Seat>() {
      @Override public boolean apply(Seat input) {
        return input.getFlightClass() == flightClass;
      }
    });
  }
}

