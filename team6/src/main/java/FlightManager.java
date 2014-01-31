import com.google.common.collect.ArrayListMultimap;
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
            return seatsForFlight.size();
        } else {
            throw new FlightNotFoundException();
        }
    }

    public void addSeat(Flight flight, Seat seat) {
        seats.put(flight, seat);
    }

    public BigDecimal findCheapestSeatPrice(Flight flight) {
        List<Seat> seats1 = Lists.newArrayList(seats.get(flight));
        Collections.sort(seats1);
        return seats1.get(0).getPrice();

    }
}
