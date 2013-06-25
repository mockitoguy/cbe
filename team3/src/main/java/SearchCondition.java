import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class SearchCondition {

    String origin;
    String destinantion;

    public SearchCondition setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public SearchCondition setDestination(String destinantion) {
        this.destinantion = destinantion;
        return this;
    }

    public Predicate<Flight> predicate() {
        if (origin != null && destinantion != null) {
            return Predicates.and(new OriginPredicate(origin), new DestinationPredicate(destinantion));
        }
        if (origin != null) {
            return new OriginPredicate(origin);
        }
        if (destinantion != null) {
            return new DestinationPredicate(destinantion);
        }
        throw new IllegalStateException("SearchConditions not initialized");

    }
}
