package pl.tdd.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: pcierpiatka
 */
public class Flight {

    private static final SeatPriceComparator seatPriceComparator = new SeatPriceComparator();
    
    private String flightCode;

    private Map<String, Seat> seats= new HashMap<>();
    private String origin;
    private String destination;
    private Date flightDate;

    public Flight(String flightCode) {
        this.flightCode = flightCode;
    }

    public void addSeat(SeatClass seatClass, String seatCode, double price) {
        Seat seat = new Seat(seatClass, seatCode, price);
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
        int availableCount = 0;
        for(Seat seat : seats.values()) {

            if(seat.isAvailable()) {
                priceSum += seat.getPrice();
                availableCount++;
            }
        }
        return priceSum / availableCount;
    }


    public double getAveragePriceOfSeatsInClass(SeatClass seatClass) {
        double priceSum = 0;
        int availableCount = 0;
        for(Seat seat : seats.values()) {
            if(seat.isInClass(seatClass)) {
                priceSum += seat.getPrice();
                availableCount++;
            }
        }
        return priceSum / availableCount;
    }

    public boolean isFrom(String origin) {
        return this.origin.equalsIgnoreCase(origin);
    }

    public boolean isTo(String destination) {
        return  this.destination.equalsIgnoreCase(destination);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Date getFlightDate() {
        return flightDate;
    }

}
