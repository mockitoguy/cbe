package com.airway;

public class Seat {
    private int price;
    private String seatId;

    public Seat(String seatId, int price)  {
        this.seatId = seatId;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }


    public String getSeatId() {
        return seatId;
    }
}
