package pl.allegro.edu.tdd;

import pl.allegro.edu.tdd.domain.Flight;

public class FlightBuilder {
  private String no;

  public static FlightBuilder no(String no) {
    FlightBuilder builder = new FlightBuilder();
    builder.no = no;
    return builder;
  }

  public Flight build() {
    return new Flight(no);
  }
}