import com.google.common.base.Predicate;

public class StringCheckPredicate implements Predicate<Flight> {

    private String origin;

    public StringCheckPredicate(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean apply(Flight flight) {
        return origin.equals(flight.origin);
    }

}