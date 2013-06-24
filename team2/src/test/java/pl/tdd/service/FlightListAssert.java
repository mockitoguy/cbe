package pl.tdd.service;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

import java.util.List;

/**
 * User: pcierpiatka
 */
public class FlightListAssert extends GenericAssert<FlightListAssert, List<Flight>> {

    protected FlightListAssert(List<Flight> actual) {
        super(FlightListAssert.class, actual);
    }

    public static FlightListAssert assertThat(List<Flight> actual) {
       return new FlightListAssert(actual);
    }

    public FlightListAssert containsFlightOnlyOnRoute(String origin, String destination) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isFrom(origin)).isTrue();
            Assertions.assertThat(flight.isTo(destination)).isTrue();
        }

        return this;
    }

    public FlightListAssert containsFlightOnlyFrom(String origin) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isFrom(origin)).isTrue();
        }

        return this;
    }

    public FlightListAssert containsFlightOnlyTo(String destination) {
        for(Flight flight : actual) {
            Assertions.assertThat(flight.isTo(destination)).isTrue();
        }

        return this;
    }

    public FlightListAssert hasSize(int size) {
        Assertions.assertThat(actual).hasSize(size);
        return this;
    }
}
