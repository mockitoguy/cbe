package pl.allegro.edu.tdd;

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
    Flight flight = flightDao.findFlightByNo(flightNo);
    return flight == null ? 0 : flight.getAvailableSeats();
  }

  public long getCheapestSeatPrice(String flightNo) {
    List<Seat> seats = seatDao.findSeatByFlightNoOrderByPriceDesc(flightNo);
    Seat found = null;
    for (Seat seat : seats) {
      if (found == null || seat.getPrice() < found.getPrice()) {
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
}
