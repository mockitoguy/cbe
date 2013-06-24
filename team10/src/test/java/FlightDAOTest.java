import org.junit.Test;

import java.util.List;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;
import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

public class FlightDAOTest {

    @Test
    public void shouldCreateProperDao() {
        //when
        FlightDAO flightDAO = new FlightDAO(asList(new FlightBuilder().build(), new FlightBuilder().build()));

        //then
        assertThat(flightDAO.count()).isEqualTo(2);
    }

    @Test
    public void shouldReturnEmptyValue() {

        //given
        FlightDAO flightDAO = new FlightDAO();

        //when
        when(flightDAO).getFlight("wrong name");

        //then
        then(caughtException()).isInstanceOf(NotExistingFlight.class);
    }

    @Test
    public void shouldReturnFlightBetweenLocations() {
        //given
        Flight flightOne = new FlightBuilder().from("Warsaw").to("Paris").build();
        Flight flightTwo = new FlightBuilder().from("Warsaw").to("Paris").build();
        Flight notMatching1 = new FlightBuilder().from("Warsaw").to("New York").build();
        Flight notMatching2 = new FlightBuilder().from("Hongkong").to("Paris").build();
        FlightDAO flightDAO = new FlightDAO(asList(flightOne, flightTwo, notMatching1, notMatching2));

        //when
        List<Flight> flights = flightDAO.findFlightBetween("Warsaw", "Paris");

        //then
        assertThat(flights).containsOnly(flightOne, flightTwo);
    }
}
