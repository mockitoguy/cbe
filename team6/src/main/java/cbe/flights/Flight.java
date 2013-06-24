package cbe.flights;

/**
 * @author tskrobol
 */
public class Flight {
    private String flightName;
    private int maxSeatsCount;
    private Seat[] seats;
    private Seat cheapestSeat;

    public Flight(String flightName, int availableSeats) {
        this.flightName = flightName;
        this.maxSeatsCount = availableSeats;
        this.seats = new Seat[maxSeatsCount];
        for (int i = 0; i < maxSeatsCount; i++) {
            seats[i] = new Seat();

        }
    }


    public void setSeatPrice(int seatId, double price) {
        seats[seatId].setPrice(price);
    }

    public String getFlightName() {
        return flightName;
    }

    public int getMaxSeatsCount() {
        return maxSeatsCount;
    }

    public Seat getCheapestSeat() {
        Seat cheapestSeat = getSeat(0);
        for (Seat seat : seats) {
            if (cheapestSeat.getPrice() > seat.getPrice())
                cheapestSeat = seat;
        }
        return cheapestSeat;
    }

    private Seat getSeat(int seatId) {
        return seats[seatId];
    }


    public boolean bookSeat(int seatID, User user) {
        Seat seat = getSeat(seatID);

        return seat.bookForUser(user);

    }

    public double getNotBookedAvaragePrice() {
        double notBookedAvaragePrice = 0d;
        int seatCount = 0;
        for (Seat seat : seats)
            if (!seat.isBooked()) {
                notBookedAvaragePrice += seat.getPrice();
                seatCount++;
            }
        return notBookedAvaragePrice/seatCount;
    }
}
