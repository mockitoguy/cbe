package com.allegro.cbe;

import org.junit.Test;

import java.util.List;

import static com.allegro.cbe.FlightTestBuilder.flight;
import static com.allegro.cbe.SeatTestBuilder.seat;
import static org.fest.assertions.Assertions.assertThat;

public class FlightReservationSystemTest {

    FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

    @Test
    public void shouldCheckAvailableSeatsCount() throws Throwable {
        //given
        flightReservationSystem.addFlight(flight().withNumber("ABC-123").build(),
                                            seat().build(),
                                            seat().build());

        flightReservationSystem.addFlight(flight().withNumber("XYZ-666").build(),
                                            seat().build());

        //when
        int availableSeats = flightReservationSystem.checkAvailableSeats("ABC-123");

        //then
        assertThat(availableSeats).isEqualTo(2);
    }

    @Test
    public void shouldFindCheapestSeatPerFlight() throws Throwable {
        //given
        flightReservationSystem.addFlight(flight().withNumber("LOT-123").build(),
                                            seat().withPrice(10f).build(),
                                            seat().withPrice(15f).build());

        flightReservationSystem.addFlight(flight().withNumber("XYZ-665").build(),
                                            seat().withPrice(1f).build());

        //when
        float price = flightReservationSystem.findCheapestSeatPrice("LOT-123");

        //then
        assertThat(price).isEqualTo(10f);
    }

    @Test
    public void shouldBookSeat() throws Throwable {
        //given
        flightReservationSystem.addFlight(flight().withNumber("LOT-123").build(),
                                            seat().withNumber("1").build(),
                                            seat().withNumber("2").build());

        flightReservationSystem.addFlight(flight().withNumber("XYZ-665").build(),
                                            seat().withNumber("1").build());

        //when
        flightReservationSystem.bookSeat("LOT-123", "1");

        //then
        assertThat(flightReservationSystem.checkAvailableSeats("LOT-123")).isEqualTo(1);
    }

    @Test
    public void shouldFindAveragePriceOfAvailableSeats() throws Throwable {
        //given
        flightReservationSystem.addFlight(flight().withNumber("LOT-123").build(),
                                            seat().withPrice(1f).build(),
                                            seat().withPrice(3f).build(),
                                            seat().withPrice(1000f).book().build());

        //when
        float averagePrice = flightReservationSystem.findAveragePriceOfAvailableSeats("LOT-123");

        //then
        assertThat(averagePrice).isEqualTo(2f);
    }

    // ------------------------------------------------------------------------------------------------------
    // 2nd Iteration

    @Test
    public void shouldFindMatchingFlightsForRoute() {
        //given
        Flight flight1 = new Flight("LOT-123", "Warsaw", "Poznan", "10-01-2014");
        Flight flight2 = new Flight("XYZ-666", "Warsaw", "Poznan", "20-02-2014");
        Flight flight3 = new Flight("ABC-007", "Warsaw", "Cracow", "20-02-2014");

        flightReservationSystem.addFlight(flight1);
        flightReservationSystem.addFlight(flight2);
        flightReservationSystem.addFlight(flight3);

        //when
        List<FlightInfo> flights = flightReservationSystem.findFlights("Warsaw", "Poznan");

        //then
        assertThat(flights).containsOnly(new FlightInfo("LOT-123", "10-01-2014"),
                                        new FlightInfo("XYZ-666", "20-02-2014"));
    }

    @Test
    public void shouldFindMatchingFlightsForGivenOrigin() {
        //given
        flightReservationSystem.addFlight(flight("LOT-123").withOrigin("Warsaw").build());
        flightReservationSystem.addFlight(flight("XYZ-666").withOrigin("Warsaw").build());
        flightReservationSystem.addFlight(flight("QWE-007").withOrigin("Cracow").build());

        //when
        List<FlightInfo> flights = flightReservationSystem.findFlightsFrom("Warsaw");

        //then
        assertThat(flights).containsOnly(new FlightInfo("LOT-123"), new FlightInfo("XYZ-666"));
    }

    @Test
    public void shouldFindMatchingFlightsForGivenDestination() {
        //given
        flightReservationSystem.addFlight(flight("LOT-123").withDestination("Poznan").build());
        flightReservationSystem.addFlight(flight("XYZ-666").withDestination("Poznan").build());
        flightReservationSystem.addFlight(flight("QWE-007").withDestination("Cracow").build());

        //when
        List<FlightInfo> flights = flightReservationSystem.findFlightsTo("Poznan");

        //then
        assertThat(flights).containsOnly(new FlightInfo("LOT-123"), new FlightInfo("XYZ-666"));
    }
}
