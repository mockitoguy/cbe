import com.google.common.base.Predicate;

public class OriginPredicate implements Predicate<Flight> {

    private String origin;

    public OriginPredicate(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean apply(Flight flight) {
        return origin.equals(flight.origin);
    }

}