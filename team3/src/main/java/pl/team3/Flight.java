package pl.team3;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Flight {
    private final Seats seats;
    private final String flightName;
    private final String origin;
    private final String destination;
    private final Date flightDate;


    public Flight(List<Seat> seats, String flightName, String from, String to, Date flightDate) {
        if (seats == null) {
            seats = new ArrayList<Seat>();
        }
        this.seats = new Seats(seats);
        this.flightName = flightName;
        this.origin = from;
        this.destination = to;
        this.flightDate = flightDate;
    }
    public String getFlightName() {
        return flightName;
    }

    public List<Seat> getSeats() {

        return seats.getSeats();
    }

    public List<Seat> getCheapestSeat() {

        return seats.getCheapestSeat();
    }

    private boolean isFrom(String from) {
        if (from == null) {
            return true;
        }
        return from.equals(this.origin);
    }

    private boolean isTo(String to) {
        if (to == null) {
            return true;
        }
        return to.equals(this.destination);
    }
    public boolean isFromTo(String from, String to) {
        if(from == null && to == null) {
            return true;
        }
        return isFrom(from) && isTo(to);
    }

    public static class Builder {
        private List<Seat> seats;
        private String flightName;
        private String from;
        private String to;
        private Date flightDate;

        public Builder(String no) {
            this.flightName = no;
            seats = new ArrayList<Seat>();
        }

        public Builder addSeat(Seat seat) {
            seats.add(seat);
            return this;
        }

        public Builder origin(String from) {
            this.from = from;
            return this;
        }

        public Builder destination(String to) {
            this.to = to;
            return this;
        }

        public Builder flightDate(Date flightDate) {
            this.flightDate = flightDate;
            return this;
        }

        public Flight build() {
            return new Flight(seats, flightName, from, to, flightDate);
        }

    }


}
