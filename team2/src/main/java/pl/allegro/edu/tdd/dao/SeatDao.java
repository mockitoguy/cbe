package pl.allegro.edu.tdd.dao;

import java.math.BigDecimal;
import java.util.List;

import pl.allegro.edu.tdd.domain.Seat;

public interface SeatDao {
  List<Seat> findSeatByFlightNoOrderByPriceDesc(String flightNo);

  Seat findSeatByFlightNoAndSeatNo(String flightNo, String seatNo);

  void save(Seat seat);

  int countAvailableSeats(String flightNo);

  BigDecimal calculateAveragePriceForAvailable(String flightNo);
}
