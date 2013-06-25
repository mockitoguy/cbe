package pl.allegro.edu.tdd;

import pl.allegro.edu.tdd.domain.Flight;

public class FlightBuilder {
  private String no;
  private int availableSeats;

  public static FlightBuilder no(String no) {
    FlightBuilder builder = new FlightBuilder();
    builder.no = no;
    return builder;
  }

  public FlightBuilder availableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
    return this;
  }

  public Flight build() {
    return new Flight(no, availableSeats);
  }
}