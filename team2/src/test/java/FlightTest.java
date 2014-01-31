import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class FlightTest {

    Flight flight;
    private static final int INITIAL_CAPACITY = 10;
    private static final String DUMMY_ID = "DUMMY-FLIGHT";


    public void setup(){
        flight = new Flight(DUMMY_ID, INITIAL_CAPACITY);
    }

    @Test
    public void should() throws Throwable {

    }
}
