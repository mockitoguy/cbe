public class FlightBuilder {
    private String name;
    private int seatsCount;
    private Double defaultPrice = Seat.PRICE_UNDEFINED;
    private String from;
    private String to;

    public static FlightBuilder aFlight() {
        return new FlightBuilder();
    }

    public FlightBuilder setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
        return this;
    }

    public FlightBuilder setDefaultPrice(Double defaultPrice) {
        this.defaultPrice = defaultPrice;
        return this;
    }

    public FlightBuilder from(String from) {
        this.from = from;
        return this;
    }
    public FlightBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FlightBuilder to(String to) {
        this.to = to;
        return this;
    }

    public Flight build() {
        return new Flight(name, seatsCount, defaultPrice, from, to);
    }
}