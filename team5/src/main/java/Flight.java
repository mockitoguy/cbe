import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: pgrela
 */
public class Flight {
    private Map<Seat.TRAVEL_CLASS, Integer> defaultPrices = new HashMap<Seat.TRAVEL_CLASS, Integer>();
    private final String origin;
    private final String destination;
    private final Date date;

    private int seats;

    public Map<Integer, Seat> getSeatsList() {
        return seatsList;
    }

    public void setSeatsList(Map<Integer, Seat> seatsList) {
        this.seatsList = seatsList;
    }

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

    public Flight(String flightCode, int seats, String origin, String destination, Date date) {
        this.seats = seats;
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }


    private void setDefaultClassPrice(Seat.TRAVEL_CLASS travelClass, int price) {
        defaultPrices.put(travelClass, price);
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


    public double getSeatsAveragePrice(Seat.TRAVEL_CLASS seatClass) {
        int counter = 0;
        double average = 0;
        for (Seat seat : seatsList.values()) {
            if (seat.getaClass() == seatClass) {
                average += seat.getPrice();
                ++counter;
            }
        }
        return average / counter;
    }

    public static FlightBuilder createBuilder() {
        return new FlightBuilder();
    }

    public int getNumberOfSeatsWithDifferentThanDefaultPrice(Seat.TRAVEL_CLASS aClass) {

        int counter = 0;
        int defaultPrice = defaultPrices.get(aClass);
        for (Seat seat : seatsList.values()) {
            if (seat.getaClass() == aClass && seat.getPrice() != defaultPrice) {
                ++counter;
            }
        }
        return counter;
    }

    public static class FlightBuilder {
        public FlightBuilder() {
            for (Seat.TRAVEL_CLASS travelClass : Seat.TRAVEL_CLASS.values()) {
                defaultPrices.put(travelClass, 100);
            }
        }

        public Flight build() {
            Flight flight = new Flight(flightCode, seats, origin, destination, date);
            for (Map.Entry<Seat.TRAVEL_CLASS, Integer> entry : defaultPrices.entrySet()) {
                flight.setDefaultClassPrice(entry.getKey(), entry.getValue());
            }
            if (seatsList == null)
                generateSeats();
            flight.setSeatsList(seatsList);
            return flight;
        }

        public FlightBuilder withDefaultPrice(int defaultPrice) {
            return withDefaultPrice(defaultPrice, Seat.TRAVEL_CLASS.ECONOMIC);
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

        public FlightBuilder withSeatsQuantity(int seats) {
            this.seats = seats;
            return this;
        }

        public FlightBuilder withFlightCode(String flightCode) {
            this.flightCode = flightCode;
            return this;
        }

        private String origin;
        private String destination;
        private Date date;
        private int seats = 100;
        private String flightCode;
        private Map<Integer, Seat> seatsList;
        private Map<Seat.TRAVEL_CLASS, Integer> defaultPrices = new HashMap<Seat.TRAVEL_CLASS, Integer>();

        public FlightBuilder withDefaultPrice(int price, Seat.TRAVEL_CLASS aClass) {
            defaultPrices.put(aClass, price);
            return this;
        }

        public FlightBuilder generateSeats() {
            int defaultPrice = defaultPrices.get(Seat.TRAVEL_CLASS.ECONOMIC);
            seatsList = new HashMap<Integer, Seat>();
            for (int i = 0; i < seats; i++) {
                seatsList.put(i + 1, new Seat(i + 1, defaultPrice));
            }
            return this;
        }

        public FlightBuilder setSeatsClass(Seat.TRAVEL_CLASS seatClass, int... seatsNumbers) {
            if (seatsList == null)
                generateSeats();
            int defaultPrice = defaultPrices.get(seatClass);
            for (int i : seatsNumbers) {
                Seat seat = seatsList.get(i);
                seat.setClass(seatClass);
                seat.setPrice(defaultPrice);
            }
            return this;
        }
    }

}
