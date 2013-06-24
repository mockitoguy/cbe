package cbe.flights;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author tskrobol
 */
public class Flight {
    private String flightName;
    private int maxSeatsCount = 0;
    private List<Seat> seats;
    private Seat cheapestSeat;
    private Places from;
    private Places to;
    private Date date;

    public Flight(String flightName, int availableSeats, Date date, Places from, Places to) {
        this.flightName = flightName;
        this.maxSeatsCount = availableSeats;
        this.from = from;
        this.to = to;
        this.date = date;
        this.seats = new ArrayList<Seat>(maxSeatsCount);
    }

    public void setSeatPrice(int seatId, double price) {

        getSeat(seatId).setPrice(price);

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

    public Seat getSeat(int seatId) {
        if (seatId > maxSeatsCount - 1)
            throw new IllegalArgumentException("Seat number out of bounds:" + seatId);
        if (seats.size() < seatId + 1)
            throw new IllegalArgumentException("There is no seat with id " + seatId);
        return seats.get(seatId);
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
        return notBookedAvaragePrice / seatCount;
    }

    public Places getFrom() {
        return from;
    }

    public Places getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public void addSeat(Seat seat) {
        seat.setFlight(this);
        seats.add(seat);
    }

    public HashMap<SeatClass, Double> getClassAvaragePrice() {
        //init
        HashMap<SeatClass, Double> prices = new HashMap<SeatClass, Double>();
        for(SeatClass seatClass : SeatClass.values()){
            prices.put(seatClass, 0d);
        }
        HashMap<SeatClass, Integer> counts = new HashMap<SeatClass, Integer>();
        for(SeatClass seatClass : SeatClass.values()){
            counts.put(seatClass, 0);
        }

        //init end

        for (Seat seat : seats){
            prices.put(seat.getSeatClass(), prices.get(seat.getSeatClass()) + seat.getPrice());
            counts.put(seat.getSeatClass(), (counts.get(seat.getSeatClass()))+1);
        }

        for(SeatClass seatClass : SeatClass.values()){
            prices.put(seatClass, prices.get(seatClass)/counts.get(seatClass));
        }
        return prices;

    }
}
