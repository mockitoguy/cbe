package pl.allegro.edu.tdd.dao;

import pl.allegro.edu.tdd.domain.Flight;

public interface FlightDao {

  public Flight findFlightByNo(String flightNo);
}
