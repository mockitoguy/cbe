import java.util.Map;

public class Flight {

    private Map<String, Seat> seats;

    private int availableSeats;

    private String flightNo;

    public Flight(String flightNo, int availableSeats, Map<String, Seat> seats) {
        this.flightNo = flightNo;
        this.availableSeats = availableSeats;
        this.seats = seats;
    }

    public Flight() {
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Seat bookSeat(String seatNo) throws NoSeatsAvailableException, NoSuchSeatAvailableException {
        if (availableSeats == 0) throw new NoSeatsAvailableException();

        availableSeats -= 1;

        Seat seat = seats.get(seatNo);

        if (seat == null) throw new NoSuchSeatAvailableException();

        if (seat.isBooked()) throw new NoSuchSeatAvailableException();

        seat.setBooked(true);

        return seat;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public long getCheapestSeatPrice() throws NoSeatsAvailableException {
        if (availableSeats == 0) {
            throw new NoSeatsAvailableException();
        }

        long minimumPrice = Long.MAX_VALUE;

        for (Seat s : seats.values()) {
            if (!s.isBooked()) {
                long seatPrice = s.getPrice();
                if (seatPrice < minimumPrice) {
                    minimumPrice = seatPrice;
                }
            }
        }

        return minimumPrice;
    }

    public double getAveragePrice() {
        return 0;
    }
}
