import java.math.BigDecimal;
import java.util.List;

public class FlightManager {

  private FlightRepository flightRepository;

  public void setFlightRepository(FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  public int getAvailableSeats(String flightNo) {
    return flightRepository.getFlightSeatsForFlight(flightNo).size();
  }

  public BigDecimal getCheapestSeatPrice(String flightNo) {
    List<FlightRepository.Seat> seats = flightRepository.getFlightSeatsForFlight(flightNo);
    BigDecimal minPrice = seats.get(0).price;
    for (FlightRepository.Seat seat : seats) {
      if(seat.price.compareTo(minPrice) < 0) {
        minPrice=seat.price;
      }
    }

    return minPrice;  //To change body of created methods use File | Settings | File Templates.
  }

  public void bookSeatOnFlight(String flightNo) {
    List<FlightRepository.Seat> seats = flightRepository.getFlightSeatsForFlight(flightNo);
    seats.remove(0);
  }

  public BigDecimal averagePriceNonBookedSeats(String flightNo) {
    List<FlightRepository.Seat> seats = flightRepository.getFlightSeatsForFlight(flightNo);

    BigDecimal sum = BigDecimal.ZERO;
    for (FlightRepository.Seat seat : seats) {
      sum = sum.add(seat.price);
    }
    return sum.divide(new BigDecimal(seats.size()));
  }

}
