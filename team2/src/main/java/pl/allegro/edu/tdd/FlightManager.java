package pl.allegro.edu.tdd;

import java.math.BigDecimal;
import java.util.List;

import pl.allegro.edu.tdd.dao.FlightDao;
import pl.allegro.edu.tdd.dao.SeatDao;
import pl.allegro.edu.tdd.domain.Flight;
import pl.allegro.edu.tdd.domain.Seat;
import pl.allegro.edu.tdd.exception.SeatAlreadyBookedException;

public class FlightManager {

  private FlightDao flightDao;

  private SeatDao seatDao;

  public int getAvailableSeats(String flightNo) {
    return seatDao.countAvailableSeats(flightNo);
  }

  public BigDecimal getCheapestSeatPrice(String flightNo) {
    List<Seat> seats = seatDao.findSeatByFlightNoOrderByPriceDesc(flightNo);
    Seat found = null;
    for (Seat seat : seats) {
      if (found == null || seat.getPrice().compareTo(found.getPrice()) < 0) {
        found = seat;
      }
    }
    if (found == null) {
      throw new NoSeatsAvailableException();
    }
    return found.getPrice();
  }

  public void bookSeat(String flightNo, String seatNo) {
    Seat seat = seatDao.findSeatByFlightNoAndSeatNo(flightNo, seatNo);
    if (seat == null) {
      throw new NoSeatsAvailableException();
    } else if (!seat.isAvailable()) {
      throw new SeatAlreadyBookedException();
    }
    seat.setAvailable(false);
    seatDao.save(seat);
  }

  public BigDecimal calculateAveragePriceForAvailableSeats(String flighNo) {
    return seatDao.calculateAveragePriceForAvailable(flighNo);
  }

  public List<Flight> findFlightsBetween(Place origin, Place destination) {
    return flightDao.findFlightsBetween(origin, destination);
  }
}
