import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class FlightDAOTest {


    @Test
    public void shouldReturnEmptyValue() {
        //given
        FlightDAO flightDAO = new FlightDAO();
        //when
        catchException(flightDAO).getFlight("inny");

        assertThat(caughtException()).isInstanceOf(NotExistingFlight.class);


        //then
    }

}
