package pl.tdd.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static pl.tdd.service.FlightTestBuilder.*;


/**
 * User: pcierpiatka
 */
public class FlightServiceTest {

    private FlightService flightService;

    @Before
    public void init() {
        flightService = new FlightService();
    }

    @Test
    public void shouldTellAvailableSeatsCount() {

        //given
        flightService.addFlight(flight("LA101").withSeats(5).build());
        flightService.addFlight(flight("AE500").withSeats(15).build());

        //when
        int availableSeatsCount = flightService.getAvailabeSeatsCount("LA101");

        //then
        assertThat(availableSeatsCount).isEqualTo(5);
    }

    @Test
    public void shouldThrowUnknownFlightWhenFlightDoesnExists() {

        //when
        catchException(flightService).getAvailabeSeatsCount("GHOST_FLIGHT");

        //then
        assertThat(caughtException()).isInstanceOf(UnknownFlightException.class);
    }


    @Test
    public void shouldReturnPriceOfACheapestSeatInGivenFlight() {

        // given
        double cheapestSeatPrice = 10;

        Flight testFlight = flight("L101")
                .withSeatsInPrice(20, 100)
                .withSeatsInPrice(10, 32)
                .withSeatsInPrice(1, cheapestSeatPrice).build();
        flightService.addFlight(testFlight);

        // when
        double cheapestFlightSeat = flightService.getPriceOfCheapestFlightSeat("L101");

        // then
        assertThat(cheapestFlightSeat).isEqualTo(cheapestFlightSeat);
    }

    @Test
    public void shouldBookedSeatForUserInGivenFlight() {

        //given
        Flight testFlight = flight("L102").withSeats(20).withSeat("12A").build();
        flightService.addFlight(testFlight);
        //when
        flightService.book("L102", "12A");

        //then
        assertThat(flightService.isFlightSeatAvailable("L102", "12A")).isFalse();
    }

    @Test
    public void shouldThrowUnknownSeatExceptionIfSeatDoesNotExist() {

        // given
        Flight flight = flight("L102").build();
        flightService.addFlight(flight);

        //when
        catchException(flightService).book("L102", "GHOST_SEAT");

        // then
        assertThat(caughtException()).isInstanceOf(UnknownSeatException.class);
    }

    @Test
    public void shouldThrowSeatAlreadyBookedExceptionIfSeatAlreadyBooked() {

        // given
        String bookedSeatCode = "BOOKED_SEAT";
        Flight flight = flight("L102").withBookedSeat(bookedSeatCode).build();
        flightService.addFlight(flight);

        //when
        catchException(flightService).book("L102", bookedSeatCode);

        // then
        assertThat(caughtException()).isInstanceOf(SeatAlreadyBookedException.class);

    }

    @Test
    public void shouldReturnAvgPriceOfAvailableSeatsInFlight() {

        // given
        Flight testFlight = flight("L102").withSeatsInPrice(100, 10).withSeatsInPrice(100, 20).
                withBookedSeatsInPrice(100, 10).build();
        flightService.addFlight(testFlight);

        // when
        double avgPrice = flightService.getAveragePriceOfAvailableSeats("L102");

        // then
        assertThat(avgPrice).isEqualTo(15);
    }

    @Test
    public void shouldReturnAnAveragePriceOfSeatsInClass() {
        // given
        Flight flight = flight("LA101")
                .withSeats(SeatClass.ECONOMIC, 100, 20)
                .withSeats(SeatClass.ECONOMIC, 100, 40)
                .withSeats(SeatClass.BUSINESS, 10, 100)
                .withSeats(SeatClass.FIRST, 10, 100).build();
        flightService.addFlight(flight);

        // when
        double avgPrice = flightService.getAveragePriceOfSeatsInClass("LA101", SeatClass.ECONOMIC);

        // then
        assertThat(avgPrice).isEqualTo(30);
    }

    @Test
    public void shouldReturnListOfFlightsBetweenCites() {
        // given
        flightService.addFlight(flight("L101").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L103").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L104").from("Warszawa").to("Wroclaw").on("2013-05-30").build());
        flightService.addFlight(flight("L105").from("Wroclaw").to("Poznan").on("2013-05-30").build());

        // when
        List<Flight> flightsBetween = flightService.findFlightsBetween("Warszawa", "Poznan");

        // then
        FlightListAssert.assertThat(flightsBetween).containsFlightOnlyOnRoute("Warszawa", "Poznan").hasSize(2);
    }

    @Test
    public void shouldReturnListOfFlightsFromGivenCity() {
        // given
        flightService.addFlight(flight("L101").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L103").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L104").from("Wroclaw").to("Poznan").on("2013-06-30").build());

        // when
        List<Flight> flightsBetween = flightService.findFlightsFrom("Warszawa");

        // then
        FlightListAssert.assertThat(flightsBetween).containsFlightOnlyFrom("Warszawa").hasSize(2);
    }

    @Test
    public void shouldReturnListOfFlightsToGivenCity() {
        // given
        flightService.addFlight(flight("L101").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L103").from("Warszawa").to("Poznan").on("2013-06-30").build());
        flightService.addFlight(flight("L104").from("Wroclaw").to("Warszawa").on("2013-06-30").build());

        // when
        List<Flight> flightsBetween = flightService.findFlightsTo("Poznan");

        // then
        FlightListAssert.assertThat(flightsBetween).containsFlightOnlyTo("Poznan").hasSize(2);
    }

    @Test
    public void shouldCountNumberOfSeatsThatAreMoreExpensiveThanTheDefaultPrice() {
        // given
        //flightService.addFlight(flight("LA101").withDefaultSeatPrice(SeatClass.ECONOMIC, 10).build());
        // when

        //then
    }

}
