import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pgrela
 */
public class Flight {
    private final int defaultPrice;
    private final String origin;
    private final String destination;
    private final Date date;

    private int seats;
    private String flightCode;
    Map<Integer, Seat> seatsList = new HashMap<Integer, Seat>();

    public int getSeats() {
        return seats;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Flight(String flightCode, int seats, int defaultPrice, String origin, String destination, Date date) {
        this.seats = seats;
        this.flightCode = flightCode;
        this.defaultPrice = defaultPrice;
        this.origin = origin;
        this.destination = destination;
        this.date = date;

        for (int i = 0; i < seats; i++) {
            seatsList.put(i + 1, new Seat(i + 1, defaultPrice));
        }
    }

    public void setSeatPrice(int seatNumber, int price) {
        if (seatsList.get(seatNumber) == null)
            throw new RuntimeException("No such seat!");
        seatsList.get(seatNumber).setPrice(price);
    }

    public int getCheapestSeatPrice() {
        if (seatsList.isEmpty())
            throw new RuntimeException("No seats on the flight");
        Integer cheapest = null;
        for (Seat seat : seatsList.values()) {
            if (cheapest == null || cheapest > seat.getPrice()) {
                cheapest = seat.getPrice();
            }
        }
        return cheapest;
    }

    public void bookSeat(int seatNumber) {
        getSeat(seatNumber).setBooked(true);
    }

    public Seat getSeat(int seatNumber) {
        if (seatsList.get(seatNumber) == null)
            throw new RuntimeException("No such seat!");
        return seatsList.get(seatNumber);
    }

    public double getNotBookedSeatsAveragePrice() {
        if (seatsList.isEmpty())
            throw new RuntimeException("No seats on the flight");
        double average = 0;
        int count = 0;
        for (Seat seat : seatsList.values()) {
            if (seat.isBooked() == false) {
                average = average + seat.getPrice();
                count++;
            }
        }
        if (count == 0)
            throw new RuntimeException("No not booked seats");
        return average / count;
    }

    @Override
    public String toString() {
        return "Flight{" +
            "origin='" + origin + '\'' +
            ", destination='" + destination + '\'' +
            ", seats=" + seats +
            '}';
    }

    public void setSeatsClass(int first, int last, Seat.CLASS seatClass) {
        for (int i = first; i <= last; ++i) {
            seatsList.get(i).setClass(seatClass);
        }
    }

    public double getSeatsAveragePrice(Seat.CLASS seatClass) {
        int conter = 0;
        double average = 0;
        for (Seat seat : seatsList.values()) {
            if (seat.getaClass() == seatClass) {
                average += seat.getPrice();
                ++conter;
            }
        }
        return average / conter;
    }
    public static FlightBuilder createBuilder(){
          return new FlightBuilder();
    }

    public static class FlightBuilder {
        public Flight build() {
            return new Flight(flightCode, seats, defaultPrice, origin, destination, date);
        }

        public FlightBuilder withDefaultPrice(int defaultPrice) {
            this.defaultPrice = defaultPrice;
            return this;
        }

        public FlightBuilder withOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public FlightBuilder withDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public FlightBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public FlightBuilder withSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public FlightBuilder withFlightCode(String flightCode) {
            this.flightCode = flightCode;
            return this;
        }

        private int defaultPrice=100;
        private String origin;
        private String destination;
        private Date date;
        private int seats=100;
        private String flightCode;
    }

}