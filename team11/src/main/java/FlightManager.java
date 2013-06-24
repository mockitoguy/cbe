import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 10:47
 */
public class FlightManager {
    Map<String, Integer> availableSeats = new HashMap<String, Integer>();
    List<Flight> listOfFlight = new ArrayList<Flight>();

    public Integer availableSeats(String flightNumber) {
        return availableSeats.get(flightNumber);
    }

    public void setAvailableSeats(String flightNumber, int numberOfSeats) {
        availableSeats.put(flightNumber, numberOfSeats);


    }

    public void setFlight(Flight ... flights) {
        for (Flight flight : flights) {
            listOfFlight.add(flight);
        }
    }

    public void setSeatPrice(String flightNumber, int seatsNumber, BigDecimal flightPrice) {
        for (Flight flight : listOfFlight) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.addSeat(seatsNumber, flightPrice);
                return;
            }
        }
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.addSeat(seatsNumber, flightPrice);
        listOfFlight.add(flight);

    }

    public BigDecimal getCheapestSeat(String flightNumber) {
        for (Flight flight : listOfFlight) {
            if (flight.getFlightNumber().equals(flightNumber))
                return flight.getCheapestSeat();
        }
        return null;
    }

    public Reservation bookSeat(String flightNumber, int seatNo) {

        Reservation reservation = new Reservation();
        reservation.setFlight(flightNumber);
        reservation.setSeat(seatNo);
        for (Flight flight : listOfFlight) {
            if (flight.getFlightNumber().equals(flightNumber))
                flight.addNewReservation(reservation);
            return reservation;
        }
        Flight flight = new Flight();
        flight.addNewReservation(reservation);
        listOfFlight.add(flight);
        return reservation;
    }

    public BigDecimal getAvaragePriceOfNonBookedSeats(String flightNumber) {
        for (Flight flight : listOfFlight) {
            if (flight.getFlightNumber().equals(flightNumber))
                return flight.getAveragePriceOfSeat();

        }
        return null;
    }

    public List<Flight> getAvalibleFlights(String origin, String destination) {
        List<Flight> flights = new ArrayList<Flight>();
        for (Flight flight : listOfFlight) {
            if (flight.getDestination().equals(destination) && flight.getOrigin().equals(origin))
            flights.add(flight);
        }

        return flights;
    }

    public List<Flight> getAllFlightsFromOrigin(String origin) {
        List<Flight> flights = new ArrayList<Flight>();
        for (Flight flight : listOfFlight) {
            if (flight.getOrigin().equals(origin))
                flights.add(flight);
        }
      return flights;
    }

    public List<Flight> getAllFlightsToDestination(String destination) {
        List<Flight> flights = new ArrayList<Flight>();
        for (Flight flight : listOfFlight) {
            if (flight.getDestination().equals(destination))
                flights.add(flight);
        }
        return flights;
    }
}
