import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class FlightManagerTest {
    @Test
    public void shouldKnowAvaibleSeat() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);

        // when
        int seat = flightManager.getEvaibleSeat("LH01");
        // then
        assertEquals(10, seat);

    }

    @Test
    public void shouldKnowAddedFlight() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);

        // when
        flightManager.addFlight("AA202", 20);
        int seat = flightManager.getEvaibleSeat("AA202");

        // then
        assertEquals(15, seat);
    }

    @Test
    public void shouldKnowCheapestSeat() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);

        // when
        double seat = flightManager.getCheapestSeat("AA202");

        // then
        assertEquals(13.00, seat, 00.0);

    }

    @Test
    public void shouldKnowCheapestSeat2() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);

        // when
        double seat = flightManager.getCheapestSeat("LH01");

        // then
        assertEquals(123.00, seat, 00.0);

    }

    @Test
    public void shouldKnowBookSeat() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);
        flightManager.bookSeat("LH01", "N21");
        flightManager.bookSeat("AA202", "E1");

        // when
        boolean seat = flightManager.VerifiteBookSeat("AA202", "N24");

        // then
        assertEquals(true, seat);

    }

    @Test
    public void shouldKnowBookSeat2() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);
        flightManager.bookSeat("LH01", "N21");
        flightManager.bookSeat("AA202", "E1");

        // when
        boolean seat = flightManager.VerifiteBookSeat("AA202", "E1");

        // then
        assertEquals(false, seat);

    }

    @Test
    public void shouldKnowAverageOfNonBookedSeat() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);
        flightManager.bookSeat("LH01", "N21");
        flightManager.bookSeat("AA202", "E1");

        // when
        double seat = flightManager.showAverage("LH01");

        // then
        assertEquals(12383.22, seat, 0.00);

    }

    @Test
    public void shouldKnowAverageOfNonBookedSeat2() throws Exception {
        // given
        FlightManager flightManager = new FlightManager();
        flightManager.addFlight("LH01", 10);
        flightManager.addFlight("AA202", 15);
        flightManager.addSeatPrice("LH01", "N21", 123.00);
        flightManager.addSeatPrice("LH01", "G123", 12383.22);
        flightManager.addSeatPrice("AA202", "E1", 123111.00);
        flightManager.addSeatPrice("AA202", "N24", 13.00);
        flightManager.addSeatPrice("AA202", "N23", 13.00);
        flightManager.addSeatPrice("AA202", "N22", 13.00);
        flightManager.addSeatPrice("AA202", "N21", 13.00);

        flightManager.bookSeat("LH01", "N21");
        flightManager.bookSeat("AA202", "E1");

        // when
        double seat = flightManager.showAverage("AA202");

        // then
        assertEquals(13.0, seat, 0.00);

    }
}
