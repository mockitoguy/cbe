package pl.team3;

import java.util.ArrayList;
import java.util.List;


public class Flight {
    private final Seats seats;
    private final String flightName;

    private Flight(List<Seat> seats, String flightName) {
        if (seats == null) {
            seats = new ArrayList<Seat>();
        }
        this.seats = new Seats(seats);
        this.flightName = flightName;
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

    public void addSeat(Seat seat) {

        seats.addSeat(seat);
    }

    public static class Builder {
        private List<Seat> seats;
        private String flightName;

        public Builder(String no) {
            this.flightName = no;
            seats = new ArrayList<Seat>();
        }

        public Builder addSeat(Seat seat) {
            seats.add(seat);
            return this;
        }

        public Flight build() {
            return new Flight(seats, flightName);
        }

    }


}
