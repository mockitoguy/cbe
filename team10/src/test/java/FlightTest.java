import org.junit.Assert;
import org.junit.Test;

public class FlightTest {
    @Test
    public void shouldTellSeatCount() {
        //given
        Flight flight = new Flight(5);
        //when


        //then
        Assert.assertEquals(5, flight.getSeatsCount());
    }
}
