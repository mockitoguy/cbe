package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String flightNumber;
    private List<Seat> seats;

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
        seats = new ArrayList<Seat>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Integer getAvailableSeats() {
        int availableSeats = 0;
        for (Seat seat : seats) {
            if(seat.isAvailable()) {
                availableSeats++;
            }
        }
        return availableSeats;
    }

    public BigDecimal getLowestSeatPrice() {
        BigDecimal lowestPrice = seats.get(0).getSeatPrice();
        for (Seat seat : seats) {
            if (seat.getSeatPrice().compareTo(lowestPrice) < 0) {
                lowestPrice = seat.getSeatPrice();
            }
        }
        return lowestPrice;
    }
}
