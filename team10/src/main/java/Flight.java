import java.util.ArrayList;
import java.util.List;

public class Flight {

    private final String from;
    private final String to;
    private final List<Seat> seats;
    private String name;

    public Flight(String name, int seatsCount, Double defaultPrice, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.seats = new ArrayList<Seat>(seatsCount);
        for (int i = 0; i < seatsCount; i++) {
            seats.add(i, new Seat(i, defaultPrice));
        }
    }

    public int getSeatsCount() {
        return seats.size();
    }

    public Double getSeatPrice(int seat) {
        return getSeat(seat).getPrice();
    }

    public void setSeatPrice(int seat, Double price) {
        getSeat(seat).setPrice(price);
    }

    public Double getCheapestSeatPrice() {
        Double cheapestPrice = Seat.PRICE_UNDEFINED;
        for (Seat seat : seats) {
            Double seatPrice = seat.getPrice();
            if (Seat.PRICE_UNDEFINED.equals(cheapestPrice)) {
                cheapestPrice = seatPrice;
            }
            cheapestPrice = Math.min(cheapestPrice, seatPrice);

        }
        return cheapestPrice;
    }

    public void reserveSeat(int seatNumber) {

        getSeat(seatNumber).reserve();

    }

    private Seat getSeat(int seatNumber) {
        return seats.get(seatNumber - 1);
    }

    public boolean isSeatReserved(int seat) {
        return getSeat(seat).isReserved();
    }

    public double getAveragePrice() {
        double sum = 0;
        int count = 0;

        for (Seat seat : seats) {
            if (!seat.isReserved()) {
                sum += seat.getPrice();
                count++;
            }
        }

        return sum / count;
    }

    public List<Flight> getFlightsBetween(String from, String to) {


        return null;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
