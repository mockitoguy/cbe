public class FlightBuilder {
    private int seatsCount;
    private Double defaultPrice = Seat.PRICE_UNDEFINED;

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

    public Flight createFlight() {
        return new Flight(seatsCount, defaultPrice);
    }
}