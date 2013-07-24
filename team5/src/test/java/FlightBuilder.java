public class FlightBuilder {

    private Flight flight;

    public FlightBuilder(String flightNo) {
        flight = new Flight(flightNo);
    }

    public FlightBuilder origin(String origin) {
        flight.setOrigin(origin);
        return this;
    }

    public FlightBuilder destination(String destination) {
        flight.setDestination(destination);
        return this;
    }

    public FlightBuilder date(String date) {
        flight.setDate(date);
        return this;
    }

    public Flight build() {
        return flight;
    }

}
