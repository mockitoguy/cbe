import org.joda.time.DateTime;

public class FlightBuilder {
    public static int DEFAULT_NUMBER = 100;
    public static final int DEFAULT_CAPACITY = 10;

    private String flightNumber;
    private int initialCapacity = DEFAULT_CAPACITY;
    private String origin;
    private String destination;
    private DateTime dateTime;

    public FlightBuilder flightNo(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightBuilder setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    public Flight build() {
        return new Flight(flightNumber!=null? flightNumber:"LOT-" + (DEFAULT_NUMBER++), initialCapacity, origin, destination);
    }

    public static  FlightBuilder flightBuilder() {
        return new FlightBuilder();
    }

    public FlightBuilder withOrigin(String origin) {
        this.origin =  origin;
        return this;
    }

    public FlightBuilder withDestination(String destination) {
        this.destination =  destination;
        return this;
    }

    public FlightBuilder withDate(DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public FlightBuilder between(String from, String to) {
        return withOrigin(from).withDestination(to);
    }
}