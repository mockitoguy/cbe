package pl.allegro.edu.tdd.dao;

import pl.allegro.edu.tdd.Place;
import pl.allegro.edu.tdd.domain.Flight;

import java.util.List;

public interface FlightDao {

  List<Flight> findFlightsBetween(Place origin, Place destination);
}
