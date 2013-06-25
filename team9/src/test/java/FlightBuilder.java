import net.flight.model.Flight;

public class FlightBuilder {

  private Flight flight;

  public FlightBuilder(String flightNo) {
    this.flight = new Flight(flightNo);
  }

  public FlightBuilder withOrigin(String origin) {
    flight.setOrigin(origin);
    return this;
  }

  public FlightBuilder withDestination(String destination) {
    flight.setDestination(destination);
    return this;
  }

  public Flight build() {
    return flight;
  }

}
