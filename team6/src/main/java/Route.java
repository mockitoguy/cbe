import com.google.common.base.Objects;

public class Route {

    private final String origin;
    private final String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(origin, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Route other = (Route) obj;
        return Objects.equal(this.origin, other.origin) && Objects.equal(this.destination, other.destination);
    }
}
