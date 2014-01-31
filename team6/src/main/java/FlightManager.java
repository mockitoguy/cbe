import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FlightManager {


    private final Multimap<Flight, Seat> seats = ArrayListMultimap.create();
    private Predicate<Seat> nonBookedSeatsPredicate = new Predicate<Seat>() {

        @Override
        public boolean apply(Seat seat) {
            return !seat.isBooked();
        }
    };;

    public int getAvailableSeats(Flight flight) throws FlightNotFoundException {
        Collection<Seat> seatsForFlight = seats.get(flight);
        if (!seatsForFlight.isEmpty()) {
            return FluentIterable.from(seatsForFlight).filter(nonBookedSeatsPredicate).size();

        } else {
            throw new FlightNotFoundException();
        }
    }

    public void addSeats(Flight flight, Seat... seats) {

        this.seats.putAll(flight, Arrays.asList(seats));
    }

    public BigDecimal findCheapestSeatPrice(Flight flight) {
        List<Seat> seats = Lists.newArrayList(this.seats.get(flight));
        Collections.sort(seats);
        return seats.get(0).getPrice();

    }

    public void book(Seat seat, Flight flight) {

        List<Seat> seats = Lists.newArrayList(this.seats.get(flight));
        for (Seat s : seats) {
            if (s.getNumber().equals(seat.getNumber())) {
                s.book();
                return;
            }

        }
    }

    public BigDecimal findAveragePriceForNonBookedSeats(Flight flight) {
        Collection<Seat> seatsForFlight = seats.get(flight);
        if (!seatsForFlight.isEmpty()) {
            ImmutableList<Seat> filtered = FluentIterable.from(seatsForFlight).filter(nonBookedSeatsPredicate).toList();

            return average(filtered);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal average(ImmutableList<Seat> filtered) {

        BigDecimal sum = BigDecimal.ZERO;
        for (Seat seat : filtered) {
            sum = sum.add(seat.getPrice());
        }
        return sum.divide(BigDecimal.valueOf(filtered.size()));
    }

    public List<Flight> findFlights(String origin, String destination) {

        List<Flight> flights = new ArrayList<>();
        for (Flight flight : seats.keySet()) {

            if (flight.getRoute().equals(new Flight.Route(origin, destination))) {
                flights.add(flight);
            }
        }

        return flights;
    }

    public List<Flight> findFlightsByOrigin(String origin) {
        List<Flight> flights = new ArrayList<>();
        for (Flight flight : seats.keySet()) {

            if (flight.getOrigin().equals(origin)){
                flights.add(flight) ;
            }
        }

        return flights;
    }

    public List<Flight> findFlightsByDestination(String destination) {
        List<Flight> flights = new ArrayList<>();
        for (Flight flight : seats.keySet()) {

            if (flight.getDestination().equals(destination)){
                flights.add(flight) ;
            }
        }

        return flights;
    }
}
