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
}
