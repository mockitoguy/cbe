package net.flight;

import java.math.BigDecimal;
import java.util.List;

public class FlightManager {

  private FlightRepository flightRepository;

  public void setFlightRepository(FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  public int getAvailableSeats(String flightNo) {
    List<Seat> seats = flightRepository.getFlightSeats(flightNo);
    int availableCount = 0;
    for (Seat seat : seats) {
      if (!seat.isBooked()) {
        availableCount++;
      }
    }
    return availableCount;
  }

  public BigDecimal getCheapestSeatPrice(String flightNo) {
    List<Seat> seats = flightRepository.getFlightSeats(flightNo);
    BigDecimal minPrice = seats.get(0).getPrice();
    for (Seat seat : seats) {
      if (seat.getPrice().compareTo(minPrice) < 0) {
        minPrice = seat.getPrice();
      }
    }

    return minPrice;
  }

  public boolean bookSeatOnFlight(String flightNo) {
    List<Seat> seats = flightRepository.getFlightSeats(flightNo);
    for (Seat seat : seats) {
      if (!seat.isBooked()) {
        seat.setBooked(true);
        return true;
      }
    }
    return false;
  }

  public BigDecimal averagePriceNonBookedSeats(String flightNo) {
    List<Seat> seats = flightRepository.getFlightSeats(flightNo);

    BigDecimal sum = BigDecimal.ZERO;
    for (Seat seat : seats) {
      sum = sum.add(seat.getPrice());
    }
    return sum.divide(new BigDecimal(seats.size()));
  }

}
