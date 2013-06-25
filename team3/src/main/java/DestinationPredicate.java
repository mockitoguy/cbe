import com.google.common.base.Predicate;

public class DestinationPredicate implements Predicate<Flight> {

    private String dest;

    public DestinationPredicate(String dest) {
        this.dest = dest;
    }

    @Override
    public boolean apply(Flight flight) {
        return dest.equals(flight.destinantion);
    }

}