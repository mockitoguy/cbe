public class Flight {

    private final String flightNumber;
    private int seatsCapacity;

    public Flight(String flightNumber, int initialCapacity) {
        this.flightNumber = flightNumber;
        seatsCapacity = initialCapacity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeatsCapacity() {
        return seatsCapacity;
    }
}
