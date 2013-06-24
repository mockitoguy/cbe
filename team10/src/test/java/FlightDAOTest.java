import org.junit.Test;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.then;
import static com.googlecode.catchexception.apis.CatchExceptionBdd.when;

public class FlightDAOTest {


    @Test
    public void shouldReturnEmptyValue() {

        //given
        FlightDAO flightDAO = new FlightDAO();

        //when
        when(flightDAO).getFlight("wrong name");

        //then
        then(caughtException()).isInstanceOf(NotExistingFlight.class);
    }

}
