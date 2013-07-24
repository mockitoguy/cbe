import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;

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

	@Test
	public void shouldGiveOandD() throws Exception {
		HashMap<String, String> listOfFlights = new HashMap<String, String>();
		HashMap<String, String> temp = new HashMap<String, String>();
		ArrayList<PlanFlight> planList = new ArrayList<PlanFlight>();
		temp.put("LH01", "13-06-2013");
		temp.put("LH044", "14-06-2013");
		// given
		FlightManager flightManager = new FlightManager();
		planList.add(new PlanFlight("LH01", "13-06-2013", "Gdansk", "New York"));
		planList.add(new PlanFlight("AA202", "23-06-2013", "Warszawa",
				"New York"));
		planList.add(new PlanFlight("LH044", "14-06-2013", "Gdansk", "New York"));

		// when
		listOfFlights = flightManager.getListsOfFLight(planList, "Gdansk",
				"New York");

		// then
		assertEquals(temp, listOfFlights);
	}

	@Test
	public void shouldGetFlightFromOrigin() throws Exception {
		// given
		ArrayList<String> listOfFlights = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<PlanFlight> planList = new ArrayList<PlanFlight>();
		temp.add("AA202");

		planList.add(new PlanFlight("LH01", "13-06-2013", "Gdansk", "New York"));
		planList.add(new PlanFlight("AA202", "23-06-2013", "Warszawa",
				"New York"));
		planList.add(new PlanFlight("LH044", "14-06-2013", "Gdansk", "New York"));
		FlightManager flightManager = new FlightManager(planList);
		// when
		listOfFlights = flightManager.getListFromOrigin("Warszawa");

		// then
		assertEquals(temp, listOfFlights);
	}

	@Test
	public void shouldGetFlightToDestination() throws Exception {
		// given
		ArrayList<String> listOfFlights = new ArrayList<String>();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<PlanFlight> planList = new ArrayList<PlanFlight>();
		temp.add("LH044");

		planList.add(new PlanFlight("LH01", "13-06-2013", "Gdansk", "New York"));
		planList.add(new PlanFlight("AA202", "23-06-2013", "Warszawa",
				"New York"));
		planList.add(new PlanFlight("LH044", "14-06-2013", "Gdansk", "Warszawa"));
		FlightManager flightManager = new FlightManager(planList);
		// when
		listOfFlights = flightManager.getListToDestination("Warszawa");

		// then
		assertEquals(temp, listOfFlights);
	}

}
