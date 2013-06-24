import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlightManagerTest {

    @Test
    public void shouldTellAvailableSeatsCount() throws Exception {

        //given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH101", 5);
        flightManager.addFlight("AE500", 15);

        //when
        int count = flightManager.getAvailableSeatsCount("LH101");

        //then
        assertEquals(5, count);


    }


}
