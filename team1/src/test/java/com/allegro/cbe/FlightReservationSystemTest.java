package com.allegro.cbe;

import org.junit.Test;

import static com.allegro.cbe.SeatTestBuilder.*;
import static org.fest.assertions.Assertions.assertThat;

public class FlightReservationSystemTest {

    FlightReservationSystem flightReservationSystem = new FlightReservationSystem();

    @Test
    public void shouldCheckAvailableSeatsCount() throws Throwable {
        //given
        flightReservationSystem.addFlight("ABC-123", seat().build(), seat().build());
        flightReservationSystem.addFlight("XYZ-666", seat().build());

        //when
        int availableSeats = flightReservationSystem.checkAvailableSeats("ABC-123");

        //then
        assertThat(availableSeats).isEqualTo(2);
    }

    @Test
    public void shouldFindCheapestSeatPerFlight() throws Throwable {
        //given
        flightReservationSystem.addFlight("LOT-123",
                                            seat().withPrice(10f).build(),
                                            seat().withPrice(15f).build());

        flightReservationSystem.addFlight("XYZ-665", seat().withPrice(1f).build());

        //when
        float price = flightReservationSystem.findCheapestSeatPrice("LOT-123");

        //then
        assertThat(price).isEqualTo(10f);
    }

    @Test
    public void shouldBookSeat() throws Throwable {
        //given
        flightReservationSystem.addFlight("LOT-123",
                                            seat().withNumber("1").build(),
                                            seat().withNumber("2").build());

        flightReservationSystem.addFlight("XYZ-665", seat().withNumber("1").build());

        //when
        flightReservationSystem.bookSeat("LOT-123", "1");

        //then
        assertThat(flightReservationSystem.checkAvailableSeats("LOT-123")).isEqualTo(1);
    }

    @Test
    public void shouldFindAveragePriceOfAvailableSeats() throws Throwable {
        //given
        flightReservationSystem.addFlight("LOT-123",
                                            seat().withPrice(1f).build(),
                                            seat().withPrice(3f).build(),
                                            seat().withPrice(1000f).book().build());

        //when
        float averagePrice = flightReservationSystem.findAveragePriceOfAvailableSeats("LOT-123");

        //then
        assertThat(averagePrice).isEqualTo(2f);
    }
}
