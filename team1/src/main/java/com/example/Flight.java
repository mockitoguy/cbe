package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

    private String flightNumber;
    private List<Seat> seats = new ArrayList<Seat>();
    private String from;
    private String to;
    private Date date;

    public Flight() {
    }

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<Seat>();
        for (Seat seat : seats) {
            if (seat.isAvailable()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    public BigDecimal getLowestSeatPrice() {
        BigDecimal lowestPrice = seats.get(0).getPrice();
        for (Seat seat : seats) {
            if (seat.getPrice().compareTo(lowestPrice) < 0) {
                lowestPrice = seat.getPrice();
            }
        }
        return lowestPrice;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Seat getSeat(Integer seatNumber) {
        for (Seat seat : seats) {
            if (seat.getNumber().equals(seatNumber)) {
                return seat;
            }
        }

        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
