package com.example;

import java.math.BigDecimal;

public class Seat {
    private Integer seatNumber;
    private BigDecimal seatPrice;
    private boolean available;

    public Seat(Integer seatNumber, BigDecimal seatPrice, boolean available) {
        this.seatNumber = seatNumber;
        this.seatPrice = seatPrice;
        this.available = available;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public boolean isAvailable() {
        return available;
    }
}
