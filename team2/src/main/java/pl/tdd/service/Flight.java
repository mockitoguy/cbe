package pl.tdd.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * User: pcierpiatka
 */
public class Flight {

    private static final SeatPriceComparator seatPriceComparator = new SeatPriceComparator();
    
    private String flightCode;

    private Map<String, Seat> seats= new HashMap<>();

    public Flight(String flightCode) {
        this.flightCode = flightCode;
    }

    public void addSeat(String seatCode, double price) {
        Seat seat = new Seat(seatCode, price);
        seats.put(seatCode, seat);
    }

    public int getSeatsCount() {
        return seats.size();
    }

    public String getFlightCode() {
        return flightCode;
    }

    public Double getCheapestSeatPrice() {
        return Collections.min(seats.values(), seatPriceComparator).getPrice();
    }

    public void bookSeat(String seatNumber) {
        Seat seat = getSeat(seatNumber);
        if(!seat.isAvailable()){
            throw new SeatAlreadyBookedException();
        }
        seat.book();
    }

    public boolean isSeatAvailable(String bookedSeatCode) {
        return getSeat(bookedSeatCode).isAvailable();
    }

    public Seat getSeat(String seatNumber) {
        Seat seat = seats.get(seatNumber);
        if(seat == null) {
            throw new UnknownSeatException();
        }
        return seat;
    }

    public double getAveragePriceOfAvailableSeats() {

        double priceSum = 0;
        int avaiableCount = 0;
        for(Seat seat : seats.values()) {

            if(seat.isAvailable()) {
                priceSum += seat.getPrice();
                avaiableCount++;
            }
        }
        return priceSum / avaiableCount;
    }
}
