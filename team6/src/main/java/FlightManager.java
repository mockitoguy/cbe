import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FlightManager {


    private final Multimap<Flight, Seat> seats = ArrayListMultimap.create();

    public int getAvailableSeats(Flight flight) throws FlightNotFoundException {
        Collection<Seat> seatsForFlight = seats.get(flight);
        if (!seatsForFlight.isEmpty()) {
            return FluentIterable.from(seatsForFlight).filter(new Predicate<Seat>() {

                @Override
                public boolean apply(Seat seat) {
                    return !seat.isBooked();
                }
            }).size();

        } else {
            throw new FlightNotFoundException();
        }
    }

    public void addSeat(Flight flight, Seat seat) {
        seats.put(flight, seat);
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
}
