package tdd;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.util.Assert;

public class FlightAssistant {

  private final SeatsPricesComparator seatsPricesComparator = new SeatsPricesComparator();

  public BigDecimal getCheapestPrice(Flight flight) {
    if (flight.getSeats().isEmpty()) {
      throw new IllegalArgumentException("Cannot get cheapest price for slight with no seats");
    }
    List<Seat> seats = new ArrayList<>(flight.getSeats());
    Collections.sort(seats, seatsPricesComparator);
    return seats.get(0).getPrice();
  }

  private final class SeatsPricesComparator implements Comparator<Seat> {
    @Override
    public int compare(Seat seat1, Seat seat2) {
      return seat1.getPrice().compareTo(seat2.getPrice());
    }

  }

  public boolean bookSeat(Flight flight, String seatNumber) {
    Assert.isTrue(flight.hasSeat(seatNumber), "Cannot book not existing seat");
    if (flight.isSeatBooked(seatNumber)) {
      return false;
    } else {
      flight.bookSeat(seatNumber);
      return true;
    }
  }

  public BigDecimal getAverageNonBookedSeatPrice(Flight flight) {
    Set<Seat> nonBookedSeats = flight.getNonBookedSeats();
    if(nonBookedSeats.isEmpty()){
      return null;
    }
    BigDecimal nonBookedSeatAverigePrice = new BigDecimal("0.00");
    for (Seat seat : nonBookedSeats) {
      nonBookedSeatAverigePrice = nonBookedSeatAverigePrice.add(seat.getPrice());
    }
    return nonBookedSeatAverigePrice.divide(new BigDecimal(nonBookedSeats.size()), RoundingMode.HALF_EVEN);
  }

}
