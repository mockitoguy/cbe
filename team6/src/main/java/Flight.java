import com.google.common.base.Objects;

import java.util.Date;

public class Flight {

    private final String number;
    private Route route;
    private Date date;

    public Flight(String number) {
        this.number = number;
    }

    public Flight(String number, Route route, Date date) {
        this.number = number;
        this.route = route;
        this.date = date;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(number, route, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        return Objects.equal(this.number, other.number) && Objects.equal(this.route, other.route) && Objects.equal(this.date, other.date);
    }

    public Route getRoute() {
        return route;
    }
    public String getOrigin(){
        return route.origin;
    }
    public String getDestination(){
        return route.destination;
    }
    public static class Route {

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
}
