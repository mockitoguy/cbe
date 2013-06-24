public class FlightManager {

    private final FlightDAO flightDAO;

    public FlightManager(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public int getAvailableSeatsCount(String name) {
        return flightDAO.getFlight(name).getSeatsCount();
    }

    public void addSeatPrice(String flightName, int seat, double price) {
        Flight flight = flightDAO.getFlight(flightName);

        flight.setSeatPrice(seat, price);
    }

    public double getSeatPrice(String flightName, int seat) {
        return flightDAO.getFlight(flightName).getSeatPrice(seat);
    }

    public double getCheapestSeatPrice(String flightName) {

        return flightDAO.getFlight(flightName).getCheapestSeatPrice();

    }


    public void reserveSeatInFlight(int seat, String flightName) {

        Flight flight = flightDAO.getFlight(flightName);

        flight.reserveSeat(seat);

    }

    public boolean isSeatInFlightReserved(int seat, String flightName) {
        return flightDAO.getFlight(flightName).isSeatReserved(seat);
    }

    public Double getAveragePriceInFlight(String flightName) {
        return flightDAO.getFlight(flightName).getAveragePrice();
    }
}
